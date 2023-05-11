package com.yxxmg.gof.relation.interpreter;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 观察者模式
 * @since : 2023/5/11
 */
public interface ExpressionVisitor {
    /**
     * and visit
     * 
     * @param andExpression 表达式
     */
    void visit(AndExpression andExpression);

    /**
     * or visit
     * 
     * @param orExpression 表达式
     */
    void visit(OrExpression orExpression);
}
