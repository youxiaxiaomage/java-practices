package com.yxxmg.gof.behavior.delegate;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/20
 */
public class Delegator {
    private Delegate delegate;

    public Delegator() {
        this.delegate = new Delegate();
    }

    public void doTask() {
        delegate.doTask();
    }
}
