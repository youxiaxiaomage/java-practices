package com.yxxmg.dynamic.feign.url;

import java.net.URI;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

import feign.Headers;
import feign.RequestLine;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/1
 */
@FeignClient(name = "dynamic-url-feign", url = "EMPTY")
public interface DynamicUrlClient {
    @RequestLine("POST /")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    String post(URI uri, @RequestBody String request);
}
