package com.yxxmg.gof.spring.strategy;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/19
 */
@Service
@RequiredArgsConstructor
public class SmsService {
    private final SmsStrategy smsStrategy;

    public void senderCmcc(String msg) {
        this.smsStrategy.getSenderBySmsType(SmsTypeEnum.CMCC).sender(msg);
    }

    public void senderCucc(String msg) {
        this.smsStrategy.getSenderBySmsType(SmsTypeEnum.CUCC).sender(msg);
    }

    public void senderCtcc(String msg) {
        this.smsStrategy.getSenderBySmsType(SmsTypeEnum.CTCC).sender(msg);
    }
}
