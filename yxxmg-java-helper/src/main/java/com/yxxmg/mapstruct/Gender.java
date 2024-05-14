package com.yxxmg.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/14
 */
@AllArgsConstructor
@Getter
public enum Gender implements BaseEnum {
    MALE(0, "男"), FEMALE(1, "女");

    private final Integer code;
    private final String desc;

    @Override
    public String desc() {
        return desc;
    }

    @Override
    public Integer code() {
        return code;
    }
}
