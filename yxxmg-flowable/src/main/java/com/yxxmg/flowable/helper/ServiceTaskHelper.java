package com.yxxmg.flowable.helper;

import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections.MapUtils;
import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.impl.persistence.entity.ActivityInstanceEntity;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.flowable.engine.impl.util.CommandContextUtil;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.yxxmg.flowable.utils.JsonPathUtils;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 服务任务辅助类
 * @since : 2024/6/11
 */
public class ServiceTaskHelper {
    public static void notifyServiceLog(DelegateExecution execution) {
        // 从缓存中获取act_ru_actinst表中数据
        ActivityInstanceEntity unfinishedActivityInstance =
            CommandContextUtil.getActivityInstanceEntityManager(CommandContextUtil.getCommandContext())
                .findUnfinishedActivityInstance((ExecutionEntity)execution);
        // 发布服务任务通知事件
        // todo
    }

    @SuppressWarnings("unchecked")
    public static void parseRestResponseToVariables(String response, Expression expression,
        DelegateExecution delegateExecution) {
        String responseVariableNameStr = getStringFromField(expression, delegateExecution);
        Map<String, String> responseParams = JSONObject.parseObject(responseVariableNameStr, Map.class);
        if (MapUtils.isNotEmpty(responseParams)) {
            Map<String, Object> variables = Maps.newHashMap();
            for (Map.Entry<String, String> entry : responseParams.entrySet()) {
                // 解析异常抛出异常
                variables.put(entry.getKey(), JsonPathUtils.getValue(response, entry.getValue()));
            }
            if (MapUtils.isNotEmpty(variables)) {
                // 设置流程变量
                // 提取结果内容
                delegateExecution.setVariables(variables);
            }
        }
    }

    public static String getStringFromField(Expression expression, DelegateExecution execution) {
        if (Objects.nonNull(expression)) {
            Object value = expression.getValue(execution);
            if (Objects.nonNull(value)) {
                return String.valueOf(value);
            }
        }
        return null;
    }
}
