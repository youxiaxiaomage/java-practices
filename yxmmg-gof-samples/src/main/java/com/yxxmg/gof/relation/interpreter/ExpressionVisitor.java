package com.yxxmg.gof.relation.interpreter;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 观察者模式
 * @since : 2023/5/11
 */
public interface ExpressionVisitor {
    void visit(AndExpression andExpression);

    void visit(OrExpression orExpression);
}
