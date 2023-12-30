package com.yxxmg.gof.structure.decorator;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/30
 */
public class ConcreteComponent implements Component {
    @Override
    public void operate() {
        System.out.println("do something");
    }
}
