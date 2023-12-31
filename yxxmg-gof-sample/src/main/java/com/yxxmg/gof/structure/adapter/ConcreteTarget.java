package com.yxxmg.gof.structure.adapter;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/31
 */
public class ConcreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("concrete receive request");
    }
}
