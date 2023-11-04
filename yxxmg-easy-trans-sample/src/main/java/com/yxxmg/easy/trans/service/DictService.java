package com.yxxmg.easy.trans.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fhs.trans.service.impl.DictionaryTransService;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/11/2
 */
@Service
public class DictService {
    @Resource
    private DictionaryTransService dictionaryTransService;

    @PostConstruct
    public void init() {
        // 在某处将字典缓存刷新到翻译服务中，以下是demo
        Map<String, String> transMap = new HashMap<>();
        transMap.put("0", "男");
        transMap.put("1", "女");
        dictionaryTransService.refreshCache("sex", transMap);
    }
}
