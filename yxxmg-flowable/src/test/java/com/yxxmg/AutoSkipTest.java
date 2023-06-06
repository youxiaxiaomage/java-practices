package com.yxxmg;

import com.google.common.collect.Maps;
import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.Map;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/6/3
 */
@RunWith(JUnit4.class)
public class AutoSkipTest {
    @Test
    public void test() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // Deployment deployment = repositoryService.createDeployment().addClasspathResource("自动跳过案例.bpmn20.xml")
        // .name("autoSkipTest").category("autoSkipTest").deploy();

        ProcessDefinition processDefinition =
            repositoryService.createProcessDefinitionQuery().deploymentId("15001").singleResult();
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("assignee", "123456");
        variables.put("manager", "123456");
        // org.flowable.engine.impl.interceptor.BpmnOverrideContextInterceptor
        // org.flowable.engine.impl.interceptor.CommandInvoker.execute
        // org.flowable.common.engine.impl.agenda.AgendaOperationRunner
        // org.flowable.engine.impl.agenda.TakeOutgoingSequenceFlowsOperation.handleFlowNode
        // org.flowable.engine.impl.agenda.TakeOutgoingSequenceFlowsOperation.leaveFlowNode
        // org.flowable.engine.impl.bpmn.helper.SkipExpressionUtil
        variables.put("_FLOWABLE_SKIP_EXPRESSION_ENABLED", true);

        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(), variables);
        String processInstanceId = processInstance.getProcessInstanceId();
        TaskService taskService = processEngine.getTaskService();

        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        taskService.complete(task.getId());

        Task task1 = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        System.out.println(task1);

    }

}
