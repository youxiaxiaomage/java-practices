package com.yxxmg.gof.structure.adapter.spring;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/14
 */
public class HandlerAImpl implements HandlerA {
    @Override
    public void handlerA(String data) {
        System.out.println("HandlerAImpl.handlerA data:" + data);
    }
}
