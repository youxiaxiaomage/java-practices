package com.yxxmg.gof.test.createpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.cglib.proxy.Enhancer;

import com.yxxmg.gof.create.pattern.proxy.BaseService;
import com.yxxmg.gof.create.pattern.proxy.UserMethodInterceptor;
import com.yxxmg.gof.create.pattern.proxy.UserServiceInvocationHandler;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/28
 */
@RunWith(JUnit4.class)
public class ProxyTest {

    @Test
    public void test() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class[] interfaces = new Class[] {BaseService.class};
        InvocationHandler invocationHandler = new UserServiceInvocationHandler();
        BaseService baseService = (BaseService)Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        System.out.println(baseService);
        String result = baseService.selectById("1");
        System.out.println(result);
    }

    @Test
    public void test2() {
        // cglib通过Enhancer
        Enhancer enhancer = new Enhancer();
        // 设置他的父类
        enhancer.setSuperclass(BaseService.class);
        // 设置一个方法拦截器，用来拦截方法
        enhancer.setCallback(new UserMethodInterceptor());
        // 创建代理类
        BaseService baseService = (BaseService)enhancer.create();
        System.out.println(baseService);
        String s = baseService.selectById("123");
        System.out.println(s);
    }
}
