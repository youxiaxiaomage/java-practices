package com.yxxmg.javers;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/6/9
 */
@Getter
@AllArgsConstructor
public enum CustomerTypeEnum {
    TARGET(0, "目标客户"), DONE(1, "成交客户");

    private final int code;
    private final String msg;
}