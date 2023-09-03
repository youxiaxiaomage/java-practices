package com.yxxmg.mybatis.flex.enums;

import com.mybatisflex.annotation.EnumValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/3
 */
@AllArgsConstructor
@Getter
public enum SexEnum {
    /**
     * 女
     */
    FEMALE(0, "女"), MALE(1, "男");

    @EnumValue
    private final int code;
    private final String msg;
}
