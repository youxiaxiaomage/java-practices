package com.yxxmg.gof.behavior.interpreter.sql;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 解释器模式 模仿 jsqlparser
 * @since : 2023/5/11
 */
public interface Expression {
    /**
     * 接收
     * 
     * @param expressionVisitor 访问者
     */
    void accept(ExpressionVisitor expressionVisitor);
}
