package com.yxxmg.flowable.utils;

import java.text.MessageFormat;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/29
 */
@Component
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    public static <T> T getBeanByType(Class<T> clazz) {
        Assert.notNull(applicationContext, "application context is null");
        T bean = applicationContext.getBean(clazz);
        Assert.notNull(bean, MessageFormat.format("application context get bean {0} is null", clazz.getSimpleName()));
        return bean;
    }

}
