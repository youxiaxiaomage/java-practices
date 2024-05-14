package com.yxxmg.gof.structure.adapter.spring;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/14
 */
public class HandlerAdapterA implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return handler instanceof HandlerA;
    }

    @Override
    public void handler(Object handler, Object data) {
        if (handler instanceof HandlerA && data instanceof String) {
            ((HandlerA)handler).handlerA((String)data);
        } else {
            throw new UnsupportedOperationException("HandlerAdapterA cannot handler the given handler:" + handler);
        }
    }
}
