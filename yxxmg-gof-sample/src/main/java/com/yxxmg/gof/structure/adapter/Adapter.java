package com.yxxmg.gof.structure.adapter;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/31
 */
public class Adapter extends Adaptee implements Target {
    @Override
    public void request() {
        super.doSomething();
    }
}
