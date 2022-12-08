package com.yxxmg.enums;

import java.util.function.Function;

import cn.hutool.core.util.DesensitizedUtil;
import lombok.AllArgsConstructor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2022/12/8
 */
@AllArgsConstructor
public enum SensitiveStrategy {
    /**
     * 手机
     */
    PHONE(DesensitizedUtil::mobilePhone), ID(id -> DesensitizedUtil.idCardNum(id, 3, 4)),
    EMAIL(DesensitizedUtil::email);

    private final Function<String, String> desensitizer;
}
