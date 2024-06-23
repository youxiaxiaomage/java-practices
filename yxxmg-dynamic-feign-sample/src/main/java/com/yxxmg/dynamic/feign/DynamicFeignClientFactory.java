package com.yxxmg.dynamic.feign;

import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.context.ApplicationContext;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/7/14
 */
public class DynamicFeignClientFactory<T> {
    private final FeignClientBuilder feignClientBuilder;

    public DynamicFeignClientFactory(ApplicationContext applicationContext) {
        this.feignClientBuilder = new FeignClientBuilder(applicationContext);
    }

    public T getFeignClient(final Class<T> type, String serviceId) {
        return this.feignClientBuilder.forType(type, serviceId).build();
    }
}
