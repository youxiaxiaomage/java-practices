package com.yxxmg.gof.behavior.visitor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/14
 */
public class ConcreteVisitor implements Visitor {
    @Override
    public void visit(ConcreteElementA concreteElementA) {
        concreteElementA.operation();
    }

    @Override
    public void visit(ConcreteElementB concreteElementB) {
        concreteElementB.operation();
    }
}
