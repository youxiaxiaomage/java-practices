package com.yxxmg.enums;

import java.util.Objects;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/10
 */
public enum Gender {
    FEMALE, MALE;

    public static Gender of(Integer value) {
        if (Objects.isNull(value)) {
            return null;
        }
        for (Gender gender : Gender.values()) {
            if (gender.ordinal() == value) {
                return gender;
            }
        }
        return null;
    }
}
