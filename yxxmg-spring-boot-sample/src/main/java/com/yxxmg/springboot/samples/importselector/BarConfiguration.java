package com.yxxmg.springboot.samples.importselector;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/2
 */
@Configuration
public class BarConfiguration {
    @Bean
    public Bar bbar() {
        return new Bar();
    }
}
