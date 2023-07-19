package com.yxxmg.mybatisplussample.service;

import cn.hutool.core.util.IdcardUtil;
import com.alibaba.fastjson.JSONObject;
import com.yxxmg.mybatisplussample.domain.OcrResponse;
import com.yxxmg.mybatisplussample.domain.Words;
import com.yxxmg.mybatisplussample.remote.RemoteAuthService;
import com.yxxmg.mybatisplussample.remote.RemoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2022/11/7
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BaiduService {
    private final RemoteAuthService remoteAuthService;
    private final RemoteService remoteService;
    @Value("${baidu.api.appKey}")
    private String appKey;
    @Value("${baidu.api.secretKey}")
    private String secretKey;
    private static final String PARAM_KEY = "image";

    @PostConstruct
    public void init() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // String accessToken = remoteAuthService.getAccessToken(appKey, secretKey);
        // log.info("get baidu access token:{}", accessToken);
        // AccessToken token = JSONObject.parseObject(accessToken, AccessToken.class);
        // GlobalConfig.setToken(token.getAccessToken());
        // String filePath = "";
        // File file = new File("D:\\test");
        // if (!file.exists()) {
        // log.error("文件不存在");
        // }
        // if (!file.isDirectory()) {
        // log.error("文件不是目录");
        // }
        // File[] listFiles = file.listFiles();
        // if (ObjectUtils.isEmpty(listFiles)) {
        // return;
        // }
        //
        // List<File> fileList =
        // Stream.of(listFiles).filter(f -> StringUtils.contains(f.getName(), "jpg")).collect(Collectors.toList());
        // if (CollectionUtils.isEmpty(fileList)) {
        // return;
        // }
        // List<Words> idList = Lists.newArrayList();
        // fileList.forEach(f -> {
        // idList.addAll(getIdNo(f));
        // });
        // stopWatch.stop();
        // log.info("总共身份证条数：{}，总共耗时：{}ms", CollectionUtils.size(idList), stopWatch.getTime());
        // String fileName = "D:\\身份证_20221107.xlsx";
        // File excelFile = new File(fileName);
        // if (!excelFile.exists()) {
        // excelFile
        // }
        // ExcelWriter writer = EasyExcel.write(fileName, Words.class).build();
        // WriteSheet writeSheet = EasyExcel.writerSheet("身份证").build();
        // writer.write(idList, writeSheet);
        // writer.finish();
    }

    private List<Words> getIdNo(File file) {
        byte[] bytes;
        try {
            bytes = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            return Collections.emptyList();
        }
        String encodeStr = Base64Utils.encodeToString(bytes);
        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
        paramMap.add(PARAM_KEY, encodeStr);
        String response = this.remoteService.generalBasic(GlobalConfig.getToken(), paramMap);
        log.info("response: {}", response);
        OcrResponse ocrResponse = JSONObject.parseObject(response, OcrResponse.class);
        if (Objects.isNull(ocrResponse)) {
            return Collections.emptyList();
        }
        List<Words> wordsResult = ocrResponse.getWordsResult();
        if (CollectionUtils.isEmpty(wordsResult)) {
            return Collections.emptyList();
        }
        List<Words> collect =
            wordsResult.stream().filter(words -> IdcardUtil.isValidCard(words.getWords())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(collect)) {
            return Collections.emptyList();
        }
        return collect;
    }
}
