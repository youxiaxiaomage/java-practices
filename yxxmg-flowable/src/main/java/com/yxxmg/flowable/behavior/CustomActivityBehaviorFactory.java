package com.yxxmg.flowable.behavior;

import java.util.List;

import org.flowable.bpmn.model.ServiceTask;
import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.impl.bpmn.helper.ClassDelegate;
import org.flowable.engine.impl.bpmn.parser.FieldDeclaration;
import org.flowable.engine.impl.bpmn.parser.factory.DefaultActivityBehaviorFactory;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/7/9
 */
public class CustomActivityBehaviorFactory extends DefaultActivityBehaviorFactory {
    @Override
    public RestCallTaskActivityBehavior createHttpActivityBehavior(ServiceTask serviceTask) {
        List<FieldDeclaration> fieldDeclarations = super.createFieldDeclarations(serviceTask.getFieldExtensions());
        return (RestCallTaskActivityBehavior)ClassDelegate
            .defaultInstantiateDelegate(RestCallTaskActivityBehavior.class, fieldDeclarations);
    }

    public CustomServiceTaskDelegateExpressionActivityBehavior
        createCustomServiceTaskDelegateExpressionActivityBehavior(ServiceTask serviceTask) {
        Expression delegateExpression = expressionManager.createExpression(serviceTask.getImplementation());
        return new CustomServiceTaskDelegateExpressionActivityBehavior(serviceTask.getId(), delegateExpression,
            getSkipExpressionFromServiceTask(serviceTask), createFieldDeclarations(serviceTask.getFieldExtensions()),
            serviceTask.getMapExceptions(), serviceTask.isTriggerable());
    }
}
