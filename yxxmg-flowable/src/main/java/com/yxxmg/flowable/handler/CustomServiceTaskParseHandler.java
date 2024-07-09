package com.yxxmg.flowable.handler;

import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.ImplementationType;
import org.flowable.bpmn.model.ServiceTask;
import org.flowable.engine.impl.bpmn.parser.BpmnParse;
import org.flowable.engine.impl.bpmn.parser.handler.ServiceTaskParseHandler;

import com.yxxmg.flowable.behavior.CustomActivityBehaviorFactory;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/7/9
 */
public class CustomServiceTaskParseHandler extends ServiceTaskParseHandler {
    private static final String TASK_TYPE = "rest";

    @Override
    protected void executeParse(BpmnParse bpmnParse, ServiceTask serviceTask) {
        super.executeParse(bpmnParse, serviceTask);
        if (StringUtils.isNotBlank(serviceTask.getType())) {
            if (StringUtils.equalsIgnoreCase(TASK_TYPE, serviceTask.getType())) {
                serviceTask.setBehavior(((CustomActivityBehaviorFactory)bpmnParse.getActivityBehaviorFactory())
                    .createHttpActivityBehavior(serviceTask));
            }
        } else if (ImplementationType.IMPLEMENTATION_TYPE_DELEGATEEXPRESSION
            .equalsIgnoreCase(serviceTask.getImplementationType())) {
            // 重写委派代理表达式逻辑
            serviceTask.setBehavior(((CustomActivityBehaviorFactory)bpmnParse.getActivityBehaviorFactory())
                .createCustomServiceTaskDelegateExpressionActivityBehavior(serviceTask));
        }
    }
}
