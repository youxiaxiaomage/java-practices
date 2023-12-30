package com.yxxmg.gof.create.factory.simple;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 简单工厂
 * @since : 2023/5/5
 */
public class SimplePizzaFactory {
    private static final String CHEESE = "cheese";
    private static final String PEPPER = "pepper";

    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if (CHEESE.equals(orderType)) {
            pizza = new CheesePizza();
        } else if (PEPPER.equals(orderType)) {
            pizza = new PepperPizza();
        }
        return pizza;
    }
}
