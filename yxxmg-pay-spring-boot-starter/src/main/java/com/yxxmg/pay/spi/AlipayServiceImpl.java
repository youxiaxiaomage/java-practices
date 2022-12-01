package com.yxxmg.pay.spi;

import java.text.MessageFormat;
import java.util.Date;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 支付宝支付
 * @since : 2022/12/1
 */
public class AlipayServiceImpl implements PayService {
    @Override
    public String pay(String goods, float fee) {
        return MessageFormat.format("{0} 支付宝支付成功{1}， 您已完成购买{2}", new Date(), fee, goods);
    }
}
