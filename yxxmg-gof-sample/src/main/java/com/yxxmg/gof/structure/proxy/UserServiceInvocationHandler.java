package com.yxxmg.gof.structure.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/27
 */
public class UserServiceInvocationHandler implements InvocationHandler {
    private BaseService baseService;

    public UserServiceInvocationHandler(UserService userService) {
        this.baseService = userService;
    }

    public UserServiceInvocationHandler() {
        this.baseService = new UserService();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(baseService, args);
    }
}
