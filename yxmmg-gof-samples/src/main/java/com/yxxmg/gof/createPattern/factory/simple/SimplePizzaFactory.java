package com.yxxmg.gof.createPattern.factory.simple;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 简单工厂
 * @since : 2023/5/5
 */
public class SimplePizzaFactory {
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if ("cheese".equals(orderType)) {
            pizza = new CheesePizza();
        } else if ("pepper".equals(orderType)) {
            pizza = new PepperPizza();
        }
        return pizza;
    }
}
