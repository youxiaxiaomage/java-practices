package com.flowable;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngines;
import org.flowable.engine.TaskService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/6/3
 */
@RunWith(JUnit4.class)
public class AutoSkipTest {
    @Ignore("xxx")
    @Test
    public void test() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // RepositoryService repositoryService = processEngine.getRepositoryService();
        // RuntimeService runtimeService = processEngine.getRuntimeService();
        // Deployment deployment = repositoryService.createDeployment().addClasspathResource("自动跳过案例.bpmn20.xml")
        // .name("autoSkipTest").category("autoSkipTest").deploy();
        //
        // ProcessDefinition processDefinition =
        // repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
        // Map<String, Object> variables = Maps.newHashMap();
        // variables.put("assignee", "123456");
        // variables.put("manager", "123456");
        // org.flowable.engine.impl.interceptor.BpmnOverrideContextInterceptor
        // org.flowable.engine.impl.interceptor.CommandInvoker.execute
        // org.flowable.common.engine.impl.agenda.AgendaOperationRunner
        // org.flowable.engine.impl.agenda.TakeOutgoingSequenceFlowsOperation.handleFlowNode
        // org.flowable.engine.impl.agenda.TakeOutgoingSequenceFlowsOperation.leaveFlowNode
        // org.flowable.engine.impl.bpmn.helper.SkipExpressionUtil
        // variables.put("_FLOWABLE_SKIP_EXPRESSION_ENABLED", true);
        //
        // ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(),
        // variables);
        // String processInstanceId = processInstance.getProcessInstanceId();
        TaskService taskService = processEngine.getTaskService();
        //
        // Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        taskService.complete("13");
        //
        // Task task1 = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        // System.out.println(task1);

    }

}
