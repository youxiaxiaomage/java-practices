package com.yxxmg.gof.spring.strategy;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/19
 */
public interface Sender {
    /**
     * 短信类型
     * 
     * @return 短信枚举
     */
    SmsTypeEnum smsType();

    /**
     * 发送短信
     * 
     * @param sms 短信内容
     */
    void sender(String sms);
}
