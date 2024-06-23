package com.flowable;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngines;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.common.collect.Maps;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/1/17
 */
@RunWith(JUnit4.class)
public class MyHolidayTest {
    @Ignore("xxx")
    @Test
    public void test1() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("MyHolidayUI.bpmn20.xml")
            .name("MyHolidayUI").category("请假").deploy();
        System.out.println("deployment.getId() = " + deployment.getId());
        System.out.println("deployment.getName() = " + deployment.getName());
        System.out.println("deployment.getDeploymentTime() = " + deployment.getDeploymentTime());
    }

    @Ignore("xxx")
    @Test
    public void test2() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        Deployment deployment =
            processEngine.getRepositoryService().createDeploymentQuery().deploymentId("1").singleResult();
        System.out.println("deployment.getId() = " + deployment.getId());
        System.out.println("deployment.getName() = " + deployment.getName());
        System.out.println("deployment.getDeploymentTime() = " + deployment.getDeploymentTime());
    }

    @Ignore("xxx")
    @Test
    public void test3() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<ProcessDefinition> processDefinitionList =
            processEngine.getRepositoryService().createProcessDefinitionQuery().active().list();
        processDefinitionList.forEach(processDefinition -> {
            System.out.println("processDefinition.getId() = " + processDefinition.getId());
            System.out.println("processDefinition.getName() = " + processDefinition.getName());
            System.out.println("processDefinition.getDeploymentId() = " + processDefinition.getDeploymentId());
            System.out.println("processDefinition.getKey() = " + processDefinition.getKey());
        });

    }

    @Ignore("xxx")
    @Test
    public void test4() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("XXX公司请假流程");
        System.out.println("processInstance.getId() = " + processInstance.getId());
        System.out.println("processInstance.getName() = " + processInstance.getName());
        System.out.println("processInstance.getBusinessKey() = " + processInstance.getBusinessKey());
        System.out.println("processInstance.getProcessDefinitionId() = " + processInstance.getProcessDefinitionId());
        System.out.println("processInstance.getProcessDefinitionKey() = " + processInstance.getProcessDefinitionKey());
        System.out
            .println("processInstance.getProcessDefinitionName() = " + processInstance.getProcessDefinitionName());
        System.out.println("processInstance.getProcessInstanceId() = " + processInstance.getProcessInstanceId());
    }

    @Ignore("xxx")
    @Test
    public void test5() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<ProcessInstance> processInstanceList =
            processEngine.getRuntimeService().createProcessInstanceQuery().active().list();
        processInstanceList.forEach(processInstance -> {
            System.out.println("processInstance.getProcessInstanceId() = " + processInstance.getProcessInstanceId());
            System.out
                .println("processInstance.getProcessDefinitionId() = " + processInstance.getProcessDefinitionId());
            System.out
                .println("processInstance.getProcessDefinitionName() = " + processInstance.getProcessDefinitionName());
            System.out
                .println("processInstance.getProcessDefinitionKey() = " + processInstance.getProcessDefinitionKey());
            System.out.println("==============================================================");
        });
    }

    @Ignore("xxx")
    @Test
    public void testTask() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> taskList = taskService.createTaskQuery().list();
        taskList.forEach(System.out::println);
        // Task[id=2506, name=提交请假流程]
        // Task[id=5006, name=提交请假流程]
        // taskService.delegateTask("2506", "user3");
        taskService.resolveTask("2506");
        List<Task> taskList1 = taskService.createTaskQuery().list();
        taskList1.forEach(System.out::println);

    }

    @Ignore("xxx")
    @Test
    public void test6() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Map<String, Object> variableMap = Maps.newHashMap();
        variableMap.put("comment", "同意");
        variableMap.put("approveTime", new Date());
        taskService.complete("2506", variableMap);
        // taskService.complete();
    }

    @Ignore("xxx")
    @Test
    public void test7() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> taskList = taskService.createTaskQuery().processInstanceId("2501").includeProcessVariables()
            .includeTaskLocalVariables().includeCaseVariables().includeIdentityLinks().list();
        taskList.forEach(System.out::println);
    }

    @Ignore("xxx")
    @Test
    public void test8() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<HistoricProcessInstance> historicProcessInstanceList =
            processEngine.getHistoryService().createHistoricProcessInstanceQuery().list();
        historicProcessInstanceList.forEach(System.out::println);
    }

}
