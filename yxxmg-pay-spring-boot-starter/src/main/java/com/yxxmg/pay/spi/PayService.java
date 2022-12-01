package com.yxxmg.pay.spi;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 支付服务
 * @since : 2022/12/1
 */
public interface PayService {
    /**
     * 支付内容
     *
     * @param goods 商品
     * @param fee   费用
     * @return 拼接字符串
     */
    String pay(String goods, float fee);
}
