package com.yxxmg.gof.spring.strategy;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 短信策略
 * @since : 2023/12/19
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class SmsStrategy {
    private final List<Sender> senderList; // spring 可以注入List, Map
    private static Map<SmsTypeEnum, Sender> smsTypeEnumSenderMap = Collections.emptyMap();

    @PostConstruct
    public void init() {
        if (CollectionUtils.isEmpty(senderList)) {
            return;
        }
        smsTypeEnumSenderMap = senderList.stream().map(sender -> Pair.of(sender.smsType(), sender))
            .collect(Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v1));
        log.info("load sms sender map {}", smsTypeEnumSenderMap);
    }

    public Sender getSenderBySmsType(@NonNull SmsTypeEnum smsType) {
        Sender sender = smsTypeEnumSenderMap.get(smsType);
        Assert.notNull(sender, MessageFormat.format("smsType {0} sender is null", smsType));
        return sender;
    }
}
