package com.yxxmg.gof.create.proxy;

import java.util.Collections;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/28
 */
public class CacheBaseServiceProxy implements BaseService {
    private BaseService baseService;
    private Map<String, String> cacheMap = Collections.emptyMap();

    public CacheBaseServiceProxy(BaseService baseService) {
        this.baseService = baseService;
    }

    @Override
    public String selectById(String id) {
        String s = cacheMap.get(id);
        if (StringUtils.isBlank(s)) {
            s = baseService.selectById(id);
            cacheMap.put(id, s);
        }
        return s;
    }
}
