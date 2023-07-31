package com.yxxmg.satoken.config;

import cn.dev33.satoken.strategy.SaStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/7/31
 */
@Configuration
public class SaTokenConfig {
    @Autowired
    public void rewriteSaStrategy() {
        SaStrategy.instance.createToken = (loginId, loginType) -> UUID.randomUUID().toString().replace("-", "");
    }
}
