package com.yxxmg.rabbitmq.queue;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.yxxmg.rabbitmq.domain.SenderMessage;
import com.yxxmg.rabbitmq.enums.QueueEnum;

import lombok.RequiredArgsConstructor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 优先级队列
 * @since : 2023/10/7
 */
@Service
@RequiredArgsConstructor
public class PriorityQueueSender implements QueueSender {
    private final List<PriorityQueue> priorityQueueList;
    private final RabbitTemplate rabbitTemplate;
    private static Map<QueueEnum, PriorityQueue> priorityQueueMap = Collections.emptyMap();

    @Override
    public void send(SenderMessage senderMessage) {
        PriorityQueue priorityQueue = priorityQueueMap.get(senderMessage.getQueue());
        Assert.notNull(priorityQueue, MessageFormat.format("{0} priority queue is null!", senderMessage.getQueue()));
        this.rabbitTemplate.convertAndSend(priorityQueue.exchangeName(), priorityQueue.queue().name(),
            JSON.toJSONString(senderMessage), message -> {
                message.getMessageProperties().setPriority(senderMessage.getPriority());
                return message;
            });
    }

    @PostConstruct
    public void init() {
        if (CollectionUtils.isEmpty(priorityQueueList)) {
            return;
        }
        priorityQueueMap =
            priorityQueueList.stream().map(priorityQueue -> Pair.of(priorityQueue.queue(), priorityQueue))
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v1));
    }

}
