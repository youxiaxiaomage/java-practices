package com.yxxmg.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/10
 */
@Component
@SuppressWarnings("unchecked")
public class SpringBeanUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext() == null ? null : getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String clazzName) {
        return getApplicationContext() == null ? null : (T)getApplicationContext().getBean(clazzName);
    }
}
