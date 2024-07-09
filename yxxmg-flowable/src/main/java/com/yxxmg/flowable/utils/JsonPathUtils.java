package com.yxxmg.flowable.utils;

import org.apache.commons.lang3.StringUtils;

import com.jayway.jsonpath.JsonPath;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/22
 */
@Slf4j
public class JsonPathUtils {
    public static <T> T getValue(String response, String expression) {
        if (StringUtils.isBlank(response)) {
            // 针对于response是null或者空串 需要填充流程变量
            return null;
        }
        if (StringUtils.isBlank(expression)) {
            throw new IllegalArgumentException("json path expression is null");
        }
        try {
            return JsonPath.read(response, expression);
        } catch (Exception e) {
            log.info("Get expression {} from response {} failed.", expression, response, e);
            // 防止后续流程中使用流程变量取不到导致报错
            return null;
        }
    }
}
