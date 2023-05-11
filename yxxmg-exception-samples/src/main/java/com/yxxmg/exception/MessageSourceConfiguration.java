package com.yxxmg.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author : zhaoyan
 * @version : 1.0
 * @description :
 * @since : 2023/2/26
 */
@Configuration
public class MessageSourceConfiguration {
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages/exception");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
