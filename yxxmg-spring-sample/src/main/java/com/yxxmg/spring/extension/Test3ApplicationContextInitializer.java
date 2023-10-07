package com.yxxmg.spring.extension;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : spring容器在启动时，会调用该类的initialize方法，可以在这里做一些初始化操作 springApplication.addInitializers(new
 *              TestApplicationContextInitializer()); spring.factories
 *              org.springframework.context.ApplicationContextInitializer=com.yxxmg.spring.extension.TestApplicationContextInitializer
 * @since : 2023/10/7
 */
public class Test3ApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("test3 application.yml context initializer");
    }
}
