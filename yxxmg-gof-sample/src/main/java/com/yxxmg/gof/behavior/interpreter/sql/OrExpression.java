package com.yxxmg.gof.behavior.interpreter.sql;

import lombok.NoArgsConstructor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : Or Expression
 * @since : 2023/5/11
 */
@NoArgsConstructor
public class OrExpression extends AbstractExpression {
    @Override
    public void accept(ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String getExpression() {
        return "OR";
    }

    @Override
    public OrExpression withLeftExpression(Expression expression) {
        return (OrExpression)super.withLeftExpression(expression);
    }

    @Override
    public OrExpression withRightExpression(Expression expression) {
        return (OrExpression)super.withRightExpression(expression);
    }
}
