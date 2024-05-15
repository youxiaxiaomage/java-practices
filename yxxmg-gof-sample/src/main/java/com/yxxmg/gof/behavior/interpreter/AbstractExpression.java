package com.yxxmg.gof.behavior.interpreter;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/15
 */
public abstract class AbstractExpression implements Expression {
    protected Expression left;
    protected Expression right;

    public AbstractExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
}
