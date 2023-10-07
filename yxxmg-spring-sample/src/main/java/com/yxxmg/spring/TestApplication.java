package com.yxxmg.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yxxmg.spring.extension.TestApplicationContextInitializer;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/7
 */
@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) {
        // 执行顺序 application.yml > spring.factories > main
        SpringApplication springApplication = new SpringApplication(TestApplication.class);
        springApplication.addInitializers(new TestApplicationContextInitializer());
        springApplication.run(args);
        // ApplicationContextAwareProcessor
        // BeanNameAware
        // @PostConstruct
        // org.springframework.beans.factory.InitializingBean
        // FactoryBean

    }
}
