package com.yxxmg.gof.relation.interpreter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : And Expression
 * @since : 2023/5/11
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class AndExpression extends AbstractExpression {
    private boolean useOperator = false;

    @Override
    public String getExpression() {
        return useOperator ? "&&" : "AND";
    }

    @Override
    public void accept(ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    public AndExpression(Expression leftExpression, Expression rightExpression) {
        setLeftExpression(leftExpression);
        setRightExpression(rightExpression);
    }

    @Override
    public AndExpression withLeftExpression(Expression expression) {
        return (AndExpression)super.withLeftExpression(expression);
    }

    @Override
    public AndExpression withRightExpression(Expression expression) {
        return (AndExpression)super.withRightExpression(expression);
    }

    public AndExpression withUseOperator() {
        this.setUseOperator(useOperator);
        return this;
    }
}
