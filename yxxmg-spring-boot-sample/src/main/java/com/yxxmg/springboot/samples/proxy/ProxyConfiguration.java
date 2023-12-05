package com.yxxmg.springboot.samples.proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/5
 */
@Configuration(proxyBeanMethods = true)
public class ProxyConfiguration {
    @Bean
    public Test test1() {
        System.out.println("test1");
        Test test = test2();
        System.out.println(test);
        return new Test();
    }

    @Bean
    public Test test2() {
        System.out.println("test2");
        return new Test();
    }

    public static class Test {

    }
}
