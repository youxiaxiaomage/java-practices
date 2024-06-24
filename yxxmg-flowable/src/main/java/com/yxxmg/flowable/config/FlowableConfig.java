package com.yxxmg.flowable.config;

import java.util.Collections;

import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.context.annotation.Configuration;

import com.yxxmg.flowable.generator.YxxmgIdGenerator;
import com.yxxmg.flowable.handler.GlobalEventListener;
import com.yxxmg.flowable.interceptor.YxxmgCreateUserTaskInterceptor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : flowable配置
 * @since : 2022/12/6
 */
@Configuration
public class FlowableConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {

    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        springProcessEngineConfiguration.setActivityFontName("宋体");
        springProcessEngineConfiguration.setLabelFontName("宋体");
        springProcessEngineConfiguration.setAnnotationFontName("宋体");
        springProcessEngineConfiguration.setIdGenerator(new YxxmgIdGenerator());
        springProcessEngineConfiguration.setEventListeners(Collections.singletonList(new GlobalEventListener()));
        springProcessEngineConfiguration.setCreateUserTaskInterceptor(new YxxmgCreateUserTaskInterceptor());
    }
}
