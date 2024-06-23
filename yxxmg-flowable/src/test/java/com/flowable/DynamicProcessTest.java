package com.flowable;

import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.yxxmg.flowable.command.DynamicJumpService;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/6/4
 */
@RunWith(JUnit4.class)
public class DynamicProcessTest {
    @Ignore("xxx")
    @Test
    public void test() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("特殊借款流程.bpmn20.xml")
            .name("特殊借款流程").category("特殊借款流程").deploy();
        ProcessDefinition processDefinition =
            repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

        TaskService taskService = processEngine.getTaskService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
        Task firstTask = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        taskService.complete(firstTask.getId());

        Task secondTask = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("name:" + secondTask.getName() + ",key:" + secondTask.getTaskDefinitionKey());

        DynamicJumpService dynamicJumpService = new DynamicJumpService(processEngine.getManagementService());
        dynamicJumpService.executeJump(processInstance.getId(), secondTask.getTaskDefinitionKey(), "fourthNode");

        Task fourthTask = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("name:" + fourthTask.getName() + ",key:" + fourthTask.getTaskDefinitionKey());
        processEngine.close();
    }
}
