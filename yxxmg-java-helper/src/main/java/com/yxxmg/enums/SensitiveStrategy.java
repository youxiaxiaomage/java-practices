package com.yxxmg.enums;

import java.util.function.Function;

import cn.hutool.core.util.DesensitizedUtil;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2022/12/8
 */

public enum SensitiveStrategy {
    /**
     * 手机
     */
    PHONE(DesensitizedUtil::mobilePhone), ID(id -> DesensitizedUtil.idCardNum(id, 3, 4)),
    EMAIL(DesensitizedUtil::email);

    private final Function<String, String> desensitizer;

    SensitiveStrategy(Function<String, String> desensitizer) {
        this.desensitizer = desensitizer;
    }

    public Function<String, String> desensitizer() {
        return desensitizer;
    }
}
