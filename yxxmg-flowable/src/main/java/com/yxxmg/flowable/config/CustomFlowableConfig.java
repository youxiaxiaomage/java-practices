package com.yxxmg.flowable.config;

import java.util.Collections;

import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.flowable.validation.ProcessValidator;
import org.flowable.validation.ProcessValidatorImpl;
import org.flowable.validation.validator.ValidatorSet;
import org.flowable.validation.validator.ValidatorSetFactory;
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
public class CustomFlowableConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {

    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        springProcessEngineConfiguration.setActivityFontName("宋体");
        springProcessEngineConfiguration.setLabelFontName("宋体");
        springProcessEngineConfiguration.setAnnotationFontName("宋体");
        // 自定义BPMN模型校验器（deploy才会生效）
        springProcessEngineConfiguration.setProcessValidator(processValidator());
        // 自定义id生成器
        springProcessEngineConfiguration.setIdGenerator(new YxxmgIdGenerator());
        // 自定义事件监听器
        springProcessEngineConfiguration.setEventListeners(Collections.singletonList(new GlobalEventListener()));
        // 自定义创建用户任务拦截器
        springProcessEngineConfiguration.setCreateUserTaskInterceptor(new YxxmgCreateUserTaskInterceptor());
    }

    private ProcessValidator processValidator() {
        ProcessValidatorImpl processValidator = new ProcessValidatorImpl();
        ValidatorSet processValidatorSet = new ValidatorSetFactory().createFlowableExecutableProcessValidatorSet();
        processValidator.addValidatorSet(processValidatorSet);
        return processValidator;
    }
}
