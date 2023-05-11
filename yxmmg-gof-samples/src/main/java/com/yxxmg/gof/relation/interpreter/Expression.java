package com.yxxmg.gof.relation.interpreter;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 解释器模式 模仿 jsqlparser
 * @since : 2023/5/11
 */
public interface Expression {
    void accept(ExpressionVisitor expressionVisitor);
}
