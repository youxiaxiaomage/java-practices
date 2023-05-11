package com.yxxmg.gof.create.pattern.abstractfactory;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 具体工厂
 * @since : 2023/5/5
 */
public class ConcreteFactory extends AbstractFactory {
    @Override
    AbstractProductA createProductA() {
        return new ConcreteProductA();
    }

    @Override
    AbstractProductB createProductB() {
        return new ConcreteProductB();
    }
}
