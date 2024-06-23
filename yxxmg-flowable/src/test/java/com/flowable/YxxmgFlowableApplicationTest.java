package com.flowable;

import javax.annotation.Resource;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yxxmg.flowable.YxxmgFlowableApplication;

/**
 *  
 */
@SpringBootTest(classes = YxxmgFlowableApplication.class)
@RunWith(SpringRunner.class)
public class YxxmgFlowableApplicationTest {
    @Resource
    private RepositoryService repositoryService;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;

    @Test
    public void test() {

        // 部署流程
        this.repositoryService.createDeployment().addClasspathResource("MyHolidayUI.bpmn20.xml").deploy();
        // 启动流程
        ProcessInstance holidayProcess = this.runtimeService.startProcessInstanceByKey("holidayProcess");
        System.out.println("holidayProcess.getId() = " + holidayProcess.getId());
        this.taskService.createTaskQuery().processInstanceId(holidayProcess.getId()).list().forEach(task -> {
            System.out.println("task.getName() = " + task.getName());
            // this.taskService.complete(task.getId());
        });

    }

    @Test
    public void test2() {
        // 验证运行时查询流程实例查询的关联表位act_ru_execution
        // SELECT RES.*,
        // P.KEY_ AS ProcessDefinitionKey,
        // P.ID_ AS ProcessDefinitionId,
        // P.NAME_ AS ProcessDefinitionName,
        // P.VERSION_ AS ProcessDefinitionVersion,
        // P.DEPLOYMENT_ID_ AS DeploymentId
        // FROM ACT_RU_EXECUTION RES
        // INNER JOIN ACT_RE_PROCDEF P ON RES.PROC_DEF_ID_ = P.ID_
        // WHERE RES.PARENT_ID_ IS NULL
        // AND RES.ID_ = '1804728201868591104'
        // AND RES.PROC_INST_ID_ = '1804728201868591104'
        // ORDER BY RES.ID_ ASC
        // ProcessInstance processInstance =
        // this.runtimeService.createProcessInstanceQuery().processInstanceId("1804728201868591104").singleResult();
        // System.out.println("processInstance.getId() = " + processInstance.getId());
        this.taskService.complete("1804757046176858112");
    }
}