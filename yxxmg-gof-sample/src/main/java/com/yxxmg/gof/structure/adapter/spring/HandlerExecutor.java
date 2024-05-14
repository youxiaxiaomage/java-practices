package com.yxxmg.gof.structure.adapter.spring;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/14
 */
public class HandlerExecutor {
    private List<HandlerAdapter> handlerAdapterList = new ArrayList<>();

    public HandlerExecutor() {
        handlerAdapterList.add(new HandlerAdapterA());
        handlerAdapterList.add(new HandlerAdapterB());
    }

    public void execute(Object handler, Object data) {
        for (HandlerAdapter handlerAdapter : handlerAdapterList) {
            if (handlerAdapter.supports(handler)) {
                handlerAdapter.handler(handler, data);
                return;
            }
        }
        throw new IllegalArgumentException("current handler not suitable for handler:" + handler);
    }
}
