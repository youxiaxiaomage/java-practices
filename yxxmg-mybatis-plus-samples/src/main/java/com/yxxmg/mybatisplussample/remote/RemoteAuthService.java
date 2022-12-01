package com.yxxmg.mybatisplussample.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2022/11/7
 */
@FeignClient(url = "${baidu.api.auth}", name = "baiduAuth")
public interface RemoteAuthService {
    /**
     * 获取access_token
     * 
     * @param appKey appKey
     * @param secretKey secretKey
     * @return
     */
    @PostMapping
    String getAccessToken(@RequestParam("client_id") String appKey, @RequestParam("client_secret") String secretKey);
}
