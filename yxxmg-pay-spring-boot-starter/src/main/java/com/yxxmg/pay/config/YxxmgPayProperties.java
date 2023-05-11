package com.yxxmg.pay.config;

import com.yxxmg.pay.PayMethod;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serializable;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 支付配置类
 * @since : 2022/12/1
 */
@ConfigurationProperties(prefix = "yxxmg.pay")
@Data
public class YxxmgPayProperties {
    /**
     * 支付方式
     */
    PayMethod payMethod;
    @NestedConfigurationProperty
    Version version = new Version();

    @Data
    public static class Version implements Serializable {
        private static final long serialVersionUID = -8237398393932251089L;
        private String modifiedVersion;
    }
}
