package com.yxxmg.flowable.command;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.common.engine.impl.interceptor.Command;
import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.common.engine.impl.interceptor.CommandExecutor;
import org.flowable.engine.impl.context.Context;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityManager;
import org.flowable.engine.impl.util.CommandContextUtil;
import org.flowable.engine.impl.util.ProcessDefinitionUtil;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO
 * @since : 2023/6/4
 */
@AllArgsConstructor
public class DynamicJumpCmd implements Command<Void> {
    protected String processInstanceId;
    protected String fromActivityId;
    protected String toActivityId;

    @Override
    public Void execute(CommandContext commandContext) {
        Assert.hasLength(processInstanceId, "Process Instance Id is required");
        // 实例管理类
        ExecutionEntityManager executionEntityManager = CommandContextUtil.getExecutionEntityManager();
        ExecutionEntity executionEntity = executionEntityManager.findById(this.processInstanceId);
        if (Objects.isNull(executionEntity)) {
            throw new FlowableException("Execution could not be found with id " + this.processInstanceId);
        }
        if (!executionEntity.isProcessInstanceType()) {
            throw new FlowableException(
                "execution is not a process instance type execution for id " + this.processInstanceId);
        }
        // 获取所有子执行实例
        List<ExecutionEntity> childExecutions =
            executionEntityManager.findChildExecutionsByProcessInstanceId(this.processInstanceId);

        ExecutionEntity activeExecutionEntity = null;
        for (ExecutionEntity childExecution : childExecutions) {
            if (childExecution.getCurrentActivityId().equals(this.fromActivityId)) {
                activeExecutionEntity = childExecution;
            }
        }
        if (Objects.isNull(activeExecutionEntity)) {
            throw new FlowableException("Active execution could not be found with activity id " + this.fromActivityId);
        }
        // 获取流程模型
        BpmnModel bpmnModel = ProcessDefinitionUtil.getBpmnModel(executionEntity.getProcessDefinitionId());
        // 获取当前节点
        FlowElement fromActivityElement = bpmnModel.getFlowElement(this.fromActivityId);
        // 获取目标节点
        FlowElement toActivityElement = bpmnModel.getFlowElement(this.toActivityId);
        if (Objects.isNull(fromActivityElement)) {
            throw new FlowableException(
                "Activity could not be found in process definition for id " + this.fromActivityId);
        }
        if (Objects.isNull(toActivityElement)) {
            throw new FlowableException(
                "Activity could not be found in process definition for id " + this.toActivityId);
        }
        boolean deleteParentExecution = false;
        ExecutionEntity parent = activeExecutionEntity.getParent();
        if (Objects.nonNull(fromActivityElement.getSubProcess()) && (Objects.isNull(toActivityElement.getSubProcess())
            || (!toActivityElement.getSubProcess().getId().equals(parent.getActivityId())))) {
            deleteParentExecution = true;
        }
        // 删除当前节点所在的执行实例及相关数据
        executionEntityManager.deleteExecutionAndRelatedData(activeExecutionEntity,
            "Change activity to " + this.toActivityId, false);
        // 如果是子流程节点，删除其所在的执行实例及相关数据
        if (deleteParentExecution) {
            executionEntityManager.deleteExecutionAndRelatedData(parent, "Change activity to " + this.toActivityId,
                false);
        }
        // 创建当前流程实例的子执行实例
        ExecutionEntity newChildExecution = executionEntityManager.createChildExecution(executionEntity);
        // 设置执行实例的当前活动节点为目标节点
        newChildExecution.setCurrentFlowElement(toActivityElement);
        // 向operations中压入继续流程的操作类
        Context.getAgenda().planContinueProcessOperation(newChildExecution);
        return null;
    }
}
