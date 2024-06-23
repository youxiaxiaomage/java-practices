package com.yxxmg.flowable.interceptor;

import org.flowable.engine.interceptor.CreateUserTaskAfterContext;
import org.flowable.engine.interceptor.CreateUserTaskBeforeContext;
import org.flowable.engine.interceptor.CreateUserTaskInterceptor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 创建用户任务拦截器
 * @since : 2024/6/23
 */
@Slf4j
public class YxxmgCreateUserTaskInterceptor implements CreateUserTaskInterceptor {
    @Override
    public void beforeCreateUserTask(CreateUserTaskBeforeContext context) {
        log.info("beforeCreateUserTask do something");
    }

    @Override
    public void afterCreateUserTask(CreateUserTaskAfterContext context) {
        log.info("afterCreateUserTask do something");
    }
}
