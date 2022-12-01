package com.yxxmg.mybatisplussample.remote;

import feign.QueryMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2022/11/7
 */
@FeignClient(url = "${baidu.api.ocr}", name = "baiduApi")
public interface RemoteService {
    /**
     * 通用识别
     * 
     * @param accessToken token 信息
     * @param paramMap 请求参数
     * @return
     */
    @PostMapping(value = "/accurate_basic", headers = "application/x-www-form-urlencoded")
    String generalBasic(@RequestParam("access_token") String accessToken,
        @QueryMap MultiValueMap<String, String> paramMap);

}
