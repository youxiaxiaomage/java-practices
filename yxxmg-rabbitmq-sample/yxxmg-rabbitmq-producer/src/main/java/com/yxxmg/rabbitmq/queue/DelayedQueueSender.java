package com.yxxmg.rabbitmq.queue;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yxxmg.rabbitmq.domain.SenderMessage;
import com.yxxmg.rabbitmq.enums.QueueEnum;

import lombok.RequiredArgsConstructor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 延时队列
 * @since : 2023/10/7
 */
@Service
@RequiredArgsConstructor
public class DelayedQueueSender implements QueueSender {
    private final RabbitTemplate rabbitTemplate;
    private final Map<QueueEnum, DelayedQueue> queueEnumDelayedQueueMap;

    private static Map<QueueEnum, DelayedQueue> delayedQueueMap = Collections.emptyMap();

    @Override
    public void send(SenderMessage senderMessage) {
        DelayedQueue delayedQueue = delayedQueueMap.get(senderMessage.getQueue());
        Assert.notNull(delayedQueue, MessageFormat.format("{0} delayed queue not null!", senderMessage.getQueue()));
        // 交换机 路由key
        this.rabbitTemplate.convertAndSend(delayedQueue.exchangeName(), delayedQueue.queue().name(),
            senderMessage.getMessage(), message -> {
                message.getMessageProperties().setDelay(senderMessage.getDelayTime());
                return message;
            });
    }

    @PostConstruct
    public void init() {
        DelayedQueueSender.delayedQueueMap = queueEnumDelayedQueueMap;
    }
}
