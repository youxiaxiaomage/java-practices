package com.yxxmg.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/14
 */
@Getter
@AllArgsConstructor
public enum Status implements BaseEnum {
    DISABLE(0, "停用"), ENABLE(1, "启用");

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
