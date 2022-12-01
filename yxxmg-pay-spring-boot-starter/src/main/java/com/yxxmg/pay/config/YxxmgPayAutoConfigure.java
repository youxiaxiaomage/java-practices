package com.yxxmg.pay.config;

import com.yxxmg.pay.PayMethod;
import com.yxxmg.pay.spi.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Iterator;
import java.util.Objects;
import java.util.ServiceLoader;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 自动装配类
 * @since : 2022/12/1
 */
@Configuration
@ConditionalOnMissingBean(PayService.class)
@EnableConfigurationProperties(YxxmgPayProperties.class)
public class YxxmgPayAutoConfigure {
    @Bean
    public PayService payService(YxxmgPayProperties yxxmgPayProperties) {
        ServiceLoader<PayService> serviceLoader = ServiceLoader.load(PayService.class);
        Iterator<PayService> iterator = serviceLoader.iterator();
        PayService payService = null;
        while (iterator.hasNext()) {
            payService = iterator.next();
            if (payService instanceof AlipayServiceImpl && PayMethod.ALIPAY.equals(yxxmgPayProperties.getPayMethod())) {
                break;
            }
            if (payService instanceof UnionPayServiceImpl && PayMethod.UNION.equals(yxxmgPayProperties.getPayMethod())) {
                break;
            }
            if (payService instanceof WechatPayServiceImpl && PayMethod.WECHAT.equals(yxxmgPayProperties.getPayMethod())) {
                break;
            }
            if (Objects.isNull(payService)) {
                payService = new ErrorPayServiceImpl();
            }
        }
        return payService;
    }
}
