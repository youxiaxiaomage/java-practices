package com.yxxmg.flowable.command;

import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.common.engine.api.FlowableIllegalArgumentException;
import org.flowable.common.engine.impl.interceptor.Command;
import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.engine.HistoryService;
import org.flowable.engine.impl.util.CommandContextUtil;
import org.flowable.engine.impl.util.ProcessDefinitionUtil;
import org.flowable.task.api.history.HistoricTaskInstance;

import lombok.AllArgsConstructor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/6/4
 */
@AllArgsConstructor
public class TaskRecallCmd implements Command<Void> {
    protected String taskId;

    @Override
    public Void execute(CommandContext commandContext) {
        if (StringUtils.isBlank(taskId)) {
            throw new FlowableIllegalArgumentException("task id is required");
        }
        // 获取历史服务
        HistoryService historyService = CommandContextUtil.getProcessEngineConfiguration().getHistoryService();
        HistoricTaskInstance historicTaskInstance =
            historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
        BpmnModel bpmnModel = ProcessDefinitionUtil.getBpmnModel(historicTaskInstance.getProcessDefinitionId());
        FlowElement flowElement = bpmnModel.getFlowElement(historicTaskInstance.getTaskDefinitionKey());
        return null;
    }
}
