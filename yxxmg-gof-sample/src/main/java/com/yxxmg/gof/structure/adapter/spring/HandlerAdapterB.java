package com.yxxmg.gof.structure.adapter.spring;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/14
 */
public class HandlerAdapterB implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return handler instanceof HandlerB;
    }

    @Override
    public void handler(Object handler, Object data) {
        if (handler instanceof HandlerB && data instanceof Integer) {
            ((HandlerB)handler).handlerB((Integer)data);
        } else {
            throw new UnsupportedOperationException("HandlerAdapterB cannot handler the given handler:" + handler);
        }
    }
}
