package com.yxxmg.flowable.interceptor;

import org.flowable.engine.interceptor.*;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 流程实例启动拦截器
 * @since : 2024/7/9
 */
@Slf4j
public class YxxmgStartProcessInstanceInterceptor implements StartProcessInstanceInterceptor {
    @Override
    public void beforeStartProcessInstance(StartProcessInstanceBeforeContext instanceContext) {
        log.info("before start process instance pre operation");
    }

    @Override
    public void afterStartProcessInstance(StartProcessInstanceAfterContext instanceContext) {
        log.info("after start process instance after operation");
    }

    @Override
    public void beforeStartSubProcessInstance(StartSubProcessInstanceBeforeContext instanceContext) {
        log.info("before start sub process instance pre operation");
    }

    @Override
    public void afterStartSubProcessInstance(StartSubProcessInstanceAfterContext instanceContext) {
        log.info("after start sub process instance after operation");
    }
}
