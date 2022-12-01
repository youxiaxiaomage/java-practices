package com.yxxmg.pay.config;

import com.yxxmg.pay.PayMethod;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 支付配置类
 * @since : 2022/12/1
 */
@ConfigurationProperties(prefix = "yxxmg.pay")
@Data
public class YxxmgPayProperties {
    PayMethod payMethod;
}
