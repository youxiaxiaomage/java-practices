package com.yxxmg.jwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.List;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/16
 */
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtConfig implements Serializable {
    private String secret;
    private String tokenPrefix;
    private String tokenHeader;
    private long expiration;
    private List<String> antMatchers;
}
