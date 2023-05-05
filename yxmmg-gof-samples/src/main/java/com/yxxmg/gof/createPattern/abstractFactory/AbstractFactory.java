package com.yxxmg.gof.createPattern.abstractFactory;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO
 * @since : 2023/5/5
 */
public abstract class AbstractFactory {
    /**
     * 抽象类
     *
     * @return AbstractProductA
     */
    abstract AbstractProductA createProductA();

    /**
     * 抽象类
     *
     * @return AbstractProductB
     */
    abstract AbstractProductB createProductB();
}
