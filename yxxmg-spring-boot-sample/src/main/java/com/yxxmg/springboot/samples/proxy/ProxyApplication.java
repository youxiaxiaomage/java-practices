package com.yxxmg.springboot.samples.proxy;

import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/5
 */
public class ProxyApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(ProxyConfiguration.class);
        Map<String, ProxyConfiguration.Test> applicationContextBeansOfType =
            applicationContext.getBeansOfType(ProxyConfiguration.Test.class);
        applicationContextBeansOfType.forEach((name, test) -> System.out.println(test));
    }
}
