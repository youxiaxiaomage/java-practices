package com.yxxmg.mybatisplussample.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 性别枚举类
 * @since : 2022/11/3
 */
@Getter
@RequiredArgsConstructor
public enum GenderEnum implements BaseEnum {
    /**
     * 性别
     */
    MALE(0, "男性"), FEMALE(1, "女性");

    private final Integer code;
    @JsonValue
    private final String desc;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @JsonCreator
    public static GenderEnum of(Object obj) {
        if (obj instanceof Integer) {
            for (GenderEnum genderEnum : GenderEnum.values()) {
                if (genderEnum.getCode().equals(Integer.parseInt(obj.toString()))) {
                    return genderEnum;
                }
            }
        } else if (obj instanceof String) {
            for (GenderEnum genderEnum : GenderEnum.values()) {
                if (genderEnum.getDesc().equals(obj.toString())) {
                    return genderEnum;
                }
            }
        }
        return null;
    }
}
