package com.yxxmg.flink.cdc.enums;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/8/14
 */
@Getter
@AllArgsConstructor
public enum OperationEnum {
    UPDATE("u", "更新"), DELETE("d", "删除"), CREATE("c", "创建");

    private final String op;
    private final String desc;

    public static OperationEnum of(String op) {
        if (StringUtils.isBlank(op)) {
            return null;
        }
        for (OperationEnum operation : values()) {
            if (StringUtils.equalsIgnoreCase(operation.getOp(), op)) {
                return operation;
            }
        }
        return null;
    }

}
