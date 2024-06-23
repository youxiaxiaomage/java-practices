package com.flowable;

import static org.junit.Assert.assertEquals;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.test.Deployment;
import org.flowable.engine.test.FlowableRule;
import org.flowable.task.api.Task;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2022/12/5
 */
public class FlowableRuleTest {
    @Rule
    public FlowableRule flowableRule = new FlowableRule();

    @Ignore("xxx")
    @Test
    @Deployment
    public void testFlowableRule() {
        RuntimeService runtimeService = flowableRule.getRuntimeService();
        runtimeService.startProcessInstanceByKey("ruleUsage");

        TaskService taskService = flowableRule.getTaskService();
        Task task = taskService.createTaskQuery().singleResult();
        assertEquals("My Task", task.getName());

        taskService.complete(task.getId());
        assertEquals(0, runtimeService.createProcessInstanceQuery().count());
    }
}
