package com.yxxmg.gof.structure.adapter.spring;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/14
 */
public class HandlerBImpl implements HandlerB {
    @Override
    public void handlerB(int data) {
        System.out.println("HandlerBImpl.handlerB  data:" + data);
    }
}
