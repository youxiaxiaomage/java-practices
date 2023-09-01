package com.yxmmg.dynamic.feign.url;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import feign.Feign;
import feign.Target;
import feign.codec.Decoder;
import feign.codec.Encoder;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/1
 */
@Component
@Import(FeignClientsConfiguration.class)
public class DynamicUrlService {
    private final DynamicUrlClient dynamicUrlClient;

    @Autowired
    public DynamicUrlService(Encoder encoder, Decoder decoder) {
        this.dynamicUrlClient =
            Feign.builder().encoder(encoder).decoder(decoder).target(Target.EmptyTarget.create(DynamicUrlClient.class));
    }

    public String post(String url, String request) {
        return this.dynamicUrlClient.post(URI.create(url), request);
    }
}
