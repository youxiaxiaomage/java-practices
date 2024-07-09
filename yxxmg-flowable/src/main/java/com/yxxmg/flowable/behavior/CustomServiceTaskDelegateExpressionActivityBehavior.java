package com.yxxmg.flowable.behavior;

import java.util.List;

import org.flowable.bpmn.model.MapExceptionEntry;
import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.impl.bpmn.behavior.ServiceTaskDelegateExpressionActivityBehavior;
import org.flowable.engine.impl.bpmn.parser.FieldDeclaration;

import com.yxxmg.flowable.helper.ServiceTaskHelper;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/7/9
 */
public class CustomServiceTaskDelegateExpressionActivityBehavior extends ServiceTaskDelegateExpressionActivityBehavior {
    private static final long serialVersionUID = -100307994408898597L;

    public CustomServiceTaskDelegateExpressionActivityBehavior(String serviceTaskId, Expression expression,
        Expression skipExpression, List<FieldDeclaration> fieldDeclarations, List<MapExceptionEntry> mapExceptions,
        boolean triggerable) {
        super(serviceTaskId, expression, skipExpression, fieldDeclarations, mapExceptions, triggerable);
    }

    @Override
    public void execute(DelegateExecution execution) {
        try {
            super.execute(execution);
        } finally {
            // 记录日志
            ServiceTaskHelper.notifyServiceLog(execution);
        }
    }
}
