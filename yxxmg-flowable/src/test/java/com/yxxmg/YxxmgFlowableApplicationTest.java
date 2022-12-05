package com.yxxmg;

import org.flowable.engine.test.Deployment;
import org.flowable.engine.test.FlowableTestCase;
import org.flowable.task.api.Task;

/**
 *  
 */
public class YxxmgFlowableApplicationTest extends FlowableTestCase {
    @Deployment
    public void testSimpleProcess() {
        this.runtimeService.startProcessInstanceById("simpleProcess");
        Task task = this.taskService.createTaskQuery().singleResult();
        assertEquals("My Task", task.getName());
        taskService.complete(task.getId());
        assertEquals(0, this.runtimeService.createProcessInstanceQuery().count());
    }
}