package com.yxxmg.spring.extension;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/7
 */
@Component
public class TestFactoryBean implements FactoryBean<TestFactoryBean.TestFactoryInnerBean> {
    @Override
    public TestFactoryBean.TestFactoryInnerBean getObject() throws Exception {
        System.out.println("[FactoryBean]   getObject");
        return new TestFactoryBean.TestFactoryInnerBean();
    }

    @Override
    public Class<?> getObjectType() {
        return TestFactoryBean.TestFactoryInnerBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public static class TestFactoryInnerBean {

    }

}
