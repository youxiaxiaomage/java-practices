package com.yxxmg.gof.behavior.strategy;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 电信
 * @since : 2023/12/19
 */
@Service
@Slf4j
public class CtccSender implements Sender {
    @Override
    public SmsTypeEnum smsType() {
        return SmsTypeEnum.CTCC;
    }

    @Override
    public void sender(String sms) {
        log.info("sms type {} send msg {}", smsType(), sms);
    }
}