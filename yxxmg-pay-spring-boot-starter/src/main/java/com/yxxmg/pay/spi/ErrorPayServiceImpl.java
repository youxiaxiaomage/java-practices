package com.yxxmg.pay.spi;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 异常
 * @since : 2022/12/1
 */
public class ErrorPayServiceImpl implements PayService {
    @Override
    public String pay(String goods, float fee) {
        return "服务器出现异常，无法正常支付";
    }
}
