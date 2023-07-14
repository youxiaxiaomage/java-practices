package com.yxmmg.dynamic.feign;

import lombok.RequiredArgsConstructor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 通过FeignClient工厂获取到FeignClient对象 通过制定的请求去调用生产者方法
 * @since : 2023/7/14
 */
@RequiredArgsConstructor
public class DynamicClient {
    private final DynamicFeignClientFactory<DynamicService> dynamicFeignClientFactory;

    public String post(String serviceId, String url, Object params) {
        return dynamicFeignClientFactory.getFeignClient(DynamicService.class, serviceId).post(url, params);
    }

    public String get(String serviceId, String url, Object params) {
        return dynamicFeignClientFactory.getFeignClient(DynamicService.class, serviceId).get(url, params);
    }

    public String put(String serviceId, String url, Object params) {
        return dynamicFeignClientFactory.getFeignClient(DynamicService.class, serviceId).put(url, params);
    }

    public String delete(String serviceId, String url, Object params) {
        return dynamicFeignClientFactory.getFeignClient(DynamicService.class, serviceId).delete(url, params);
    }
}
