package com.yxxmg.oss.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.yxxmg.oss.api.OssService;
import com.yxxmg.oss.config.AliyunOssProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO
 * @since : 2023/4/21
 */
@Service
@RequiredArgsConstructor
public class OssServiceImpl implements OssService {
    private final AliyunOssProperties aliyunOssProperties;
    @Override
    @SneakyThrows
    public String test() {
        OSS oss = new OSSClientBuilder().build(aliyunOssProperties.getEndpoint(), aliyunOssProperties.getAccessKeyId(),
                aliyunOssProperties.getSecretAccessKey());
        oss.putObject(aliyunOssProperties.getBucketName(), "测试.jpg",
                ResourceUtils.getFile("classpath:img.png"));
        OSSObject ossObject = oss.getObject(aliyunOssProperties.getBucketName(), "测试.jpg");
        System.out.println(ossObject);
        oss.shutdown();
        return "";
    }
}
