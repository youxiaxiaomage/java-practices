package com.yxxmg.dynamic.feign;

import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/7/14
 */
public interface DynamicService {
    /**
     * POST请求
     * 
     * @param url 请求路径
     * @param params 请求参数
     * @return 响应结果
     */
    @PostMapping("{url}")
    String post(@PathVariable String url, @RequestBody Object params);

    /**
     * GET请求
     * 
     * @param url 请求路径
     * @param params 请求参数
     * @return 响应结果
     */
    @GetMapping("{url}")
    String get(@PathVariable String url, @SpringQueryMap Object params);

    /**
     * PUT请求
     * 
     * @param url 请求路径
     * @param params 请求参数
     * @return 响应结果
     */
    @PutMapping("{url}")
    String put(@PathVariable String url, @RequestBody Object params);

    /**
     * DELETE请求
     * 
     * @param url 请求路径
     * @param params 请求参数
     * @return 响应结果
     */
    @DeleteMapping("{url}")
    String delete(@PathVariable String url, @RequestBody Object params);
}
