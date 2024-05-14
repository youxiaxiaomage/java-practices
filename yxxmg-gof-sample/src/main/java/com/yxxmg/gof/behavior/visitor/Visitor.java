package com.yxxmg.gof.behavior.visitor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/14
 */
public interface Visitor {
    void visit(ConcreteElementA concreteElementA);

    void visit(ConcreteElementB concreteElementB);
}
