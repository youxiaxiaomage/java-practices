package com.yxxmg.flowable.validator;

import java.text.MessageFormat;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.FieldExtension;
import org.flowable.bpmn.model.Process;
import org.flowable.bpmn.model.ServiceTask;
import org.flowable.bpmn.model.TaskWithFieldExtensions;
import org.flowable.validation.ValidationError;
import org.flowable.validation.validator.impl.ServiceTaskValidator;

import com.google.common.collect.Lists;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/7/9
 */
public class CustomServiceTaskValidator extends ServiceTaskValidator {
    private static final Pattern URL_REGEX =
        Pattern.compile("(http|https)://[a-zA-Z0-9]+(.[a-zA-Z0-9]+])*(:[0-9]+)?(/[a-zA-Z0-9]+)*");
    private static final List<String> VALIDATE_ATTR_LIST = Lists.newArrayList();
    static {
        VALIDATE_ATTR_LIST.add("requestUrl");
        VALIDATE_ATTR_LIST.add("requestMethod");
    }

    @Override
    protected void verifyType(Process process, ServiceTask serviceTask, List<ValidationError> errors) {
        if (StringUtils.isNotEmpty(serviceTask.getType())) {
            if (StringUtils.equalsIgnoreCase(serviceTask.getType(), "rest")) {
                validateFieldDeclarationsForRest(process, serviceTask, serviceTask.getFieldExtensions(), errors);
            } else {
                super.verifyType(process, serviceTask, errors);
            }
        }
    }

    private void validateFieldDeclarationsForRest(Process process, TaskWithFieldExtensions task,
        List<FieldExtension> fieldExtensions, List<ValidationError> errors) {
        if (CollectionUtils.isNotEmpty(VALIDATE_ATTR_LIST)) {
            VALIDATE_ATTR_LIST.forEach(attr -> validateField(attr, process, task, fieldExtensions, errors));
        }
    }

    private void validateField(String field, Process process, TaskWithFieldExtensions task,
        List<FieldExtension> fieldExtensions, List<ValidationError> errors) {
        boolean defined = false;
        String attrValue = null;
        for (FieldExtension fieldExtension : fieldExtensions) {
            if (StringUtils.equals(field, fieldExtension.getFieldName())) {
                defined = true;
                attrValue = fieldExtension.getStringValue();
            }
        }
        if (!defined) {
            addError(errors, MessageFormat.format("flowable-RestCall-no-{0}", field), process, task,
                MessageFormat.format("Rest任务节点没有配置{0}属性", field));
        }
        if (StringUtils.isBlank(attrValue)) {
            addError(errors, MessageFormat.format("flowable-RestCall-{0}", field), process, task,
                MessageFormat.format("Rest任务节点属性{0}配置错误", field));
        } else if (StringUtils.equals(field, "requestUrl") && StringUtils.isNotBlank(attrValue)
            && !URL_REGEX.matcher(attrValue).find()) {
            addError(errors, MessageFormat.format("flowable-RestCall-{0}", field), process, task,
                MessageFormat.format("Rest任务节点属性{0}格式非法", field));
        } else if (StringUtils.equals(field, "requestMethod") && StringUtils.isNotBlank(attrValue)) {
            addError(errors, MessageFormat.format("flowable-RestCall-{0}", field), process, task,
                MessageFormat.format("Rest任务节点属性{0}暂不支持", field));
        }

    }
}
