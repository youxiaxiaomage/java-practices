package com.yxxmg.gof.behavior.visitor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/14
 */
public class ConcreteElementA implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void operation() {
        System.out.println("ConcreteElementA operation");
    }
}
