package com.yxxmg.flowable.behavior;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.impl.bpmn.behavior.AbstractBpmnActivityBehavior;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.yxxmg.flowable.helper.ServiceTaskHelper;
import com.yxxmg.flowable.utils.SpringUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/7/9
 */
@Slf4j
@SuppressWarnings("all")
public class RestCallTaskActivityBehavior extends AbstractBpmnActivityBehavior {
    private static final long serialVersionUID = -9021845109942588970L;
    private static final Pattern VARIABLE_REGEX = Pattern.compile("\\$\\{([^}]*)}");
    /**
     * 请求方法 POST GET
     */
    private Expression requestMethod;
    /**
     * 请求URL
     */
    private Expression requestUrl;
    private Expression requestBody;

    private Expression saveResponseParameters;
    /**
     * 返回变量名
     */
    private Expression responseVariableName;

    @Override
    public void execute(DelegateExecution execution) {
        // 获取各属性的值 这5个属性都在校验器中做了参数检查
        String requestMethodStr = ServiceTaskHelper.getStringFromField(requestMethod, execution);
        String requestUrlStr = ServiceTaskHelper.getStringFromField(requestUrl, execution);
        String requestBodyStr = ServiceTaskHelper.getStringFromField(requestBody, execution);
        String saveResponseParametersStr = ServiceTaskHelper.getStringFromField(saveResponseParameters, execution);
        // 执行restful
        executeRestfulRequest(execution, requestUrlStr, requestMethodStr, requestBodyStr, saveResponseParametersStr);
        // 离开当前节点
        leave(execution);
    }

    /**
     * @param execution
     * @param requestUrl 请求url
     * @param requestMethodStr 请求方法
     * @param requestBodyStr 请求体
     * @param saveResponseParametersStr 是否保存响应结果到流程变量中
     */
    private void executeRestfulRequest(DelegateExecution execution, String requestUrl, String requestMethodStr,
        String requestBodyStr, String saveResponseParametersStr) {
        try {
            // 从Spring容器获取RestTemplate
            RestTemplate restTemplate = SpringUtil.getBeanByType(RestTemplate.class);
            // 组装请求Header 默认都是application/json请求与接收
            HttpHeaders restHeaders = new HttpHeaders();
            restHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            restHeaders.setContentType(MediaType.APPLICATION_JSON);

            // 组装请求体
            Map<String, Object> requestBody = new HashMap<>();
            Map<String, Object> params = JSONObject.parseObject(requestBodyStr, Map.class);
            if (MapUtils.isNotEmpty(params)) {
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    String paramKey = entry.getKey();
                    Object paramValue = entry.getValue();
                    Matcher matcher = VARIABLE_REGEX.matcher(StringUtils.replace(String.valueOf(paramValue), " ", ""));
                    if (matcher.find()) {
                        // 匹配后，从流程变量中获取参数
                        params.put(paramKey, execution.getVariable(String.valueOf(matcher.group(1))));
                    } else {
                        // 不匹配，认为就是xml配置的内容
                        params.put(paramKey, paramValue);
                    }
                }
            }
            //
            Object request = new Object();
            HttpEntity<Object> httpEntity = new HttpEntity<>(request, restHeaders);

            // 执行请求调用
            ResponseEntity<String> result =
                restTemplate.exchange(requestUrl, HttpMethod.POST, httpEntity, String.class);
            String response = result.getBody();
            log.info("Rest service task executeRestfulRequest result: {} ", response);

        } catch (Exception e) {
            log.error("ServiceTask Rest request error", e);
            // 异常就不设置值 走默认流转
            // TODO 设置默认值到流程变量中
        } finally {
            // publish ServiceTask Log
            ServiceTaskHelper.notifyServiceLog(execution);
        }

    }

}
