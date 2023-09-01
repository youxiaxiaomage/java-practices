package com.yxxmg.mybatis.flex.config;

import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.springframework.context.annotation.Configuration;

import com.mybatisflex.core.mybatis.FlexConfiguration;
import com.mybatisflex.spring.boot.ConfigurationCustomizer;

/***
 * 
 * @author : zhaoyan
 * @version : 1.0
 * @description :
 * @since : 2023/8/31
 */
@Configuration
public class MyConfigurationCustomizer implements ConfigurationCustomizer {
    @Override
    public void customize(FlexConfiguration configuration) {
        configuration.setLogImpl(StdOutImpl.class);
    }
}
