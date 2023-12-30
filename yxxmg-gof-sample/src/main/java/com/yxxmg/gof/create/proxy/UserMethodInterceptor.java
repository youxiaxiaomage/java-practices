package com.yxxmg.gof.create.proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/28
 */
public class UserMethodInterceptor implements MethodInterceptor {
    private BaseService baseService;

    public UserMethodInterceptor() {
        this.baseService = new UserService();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        return method.invoke(baseService, args);
    }
}
