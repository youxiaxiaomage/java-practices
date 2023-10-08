package com.yxxmg.rabbitmq.consumer;

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

import com.yxxmg.rabbitmq.domain.SenderMessage;
import com.yxxmg.rabbitmq.enums.QueueEnum;

import lombok.RequiredArgsConstructor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/8
 */
@Component
@RequiredArgsConstructor
public class MessageConsumerFacade {
    private final List<MessageConsumer> messageConsumerList;
    private static Map<QueueEnum, MessageConsumer> queueEnumMessageConsumerMap = Collections.emptyMap();

    @PostConstruct
    public void init() {
        if (CollectionUtils.isEmpty(messageConsumerList)) {
            return;
        }
        queueEnumMessageConsumerMap =
            messageConsumerList.stream().map(messageConsumer -> Pair.of(messageConsumer.queue(), messageConsumer))
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v1));
    }

    private static MessageConsumer getMessageConsumer(QueueEnum queueEnum) {
        MessageConsumer messageConsumer = queueEnumMessageConsumerMap.get(queueEnum);
        Assert.notNull(messageConsumer, MessageFormat.format("consumer {0} not null!", queueEnum));
        return messageConsumer;
    }

    public void consumer(SenderMessage senderMessage) {
        MessageConsumer messageConsumer = getMessageConsumer(senderMessage.getQueue());
        messageConsumer.consume(senderMessage);
    }
}
