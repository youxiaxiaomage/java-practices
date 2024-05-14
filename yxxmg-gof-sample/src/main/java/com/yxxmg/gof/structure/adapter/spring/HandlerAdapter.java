package com.yxxmg.gof.structure.adapter.spring;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/14
 */
public interface HandlerAdapter {
    boolean supports(Object handler);

    void handler(Object handler, Object data);
}
