package com.yxxmg.gof.spring.strategy;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 移动
 * @since : 2023/12/19
 */
@Service
@Slf4j
public class CmccSender implements Sender {
    @Override
    public SmsTypeEnum smsType() {
        return SmsTypeEnum.CMCC;
    }

    @Override
    public void sender(String sms) {
        log.info("sms type {} send msg {}", smsType(), sms);
    }
}
