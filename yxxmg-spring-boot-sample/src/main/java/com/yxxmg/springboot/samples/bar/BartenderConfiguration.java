package com.yxxmg.springboot.samples.bar;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/2
 */
@Configuration
public class BartenderConfiguration {
    @Bean
    public Bartender zhangxiaosan() {
        return new Bartender("张小三");
    }

    @Bean
    public Bartender zhangdasan() {
        return new Bartender("张大三");
    }
}
