package com.yxxmg.pay;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 支付宝支付
 * @since : 2022/12/1
 */
@RequiredArgsConstructor
@Getter
public enum PayMethod {
    /**
     * 支付宝支付
     */
    ALIPAY("alipay"),
    /**
     * 微信支付
     */
    WECHAT("wechat"),
    /**
     * 银联卡支付
     */
    UNION("union");
    private final String name;

}
