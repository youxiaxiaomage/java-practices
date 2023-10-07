package com.yxxmg.rabbitmq.queue;

import org.springframework.stereotype.Component;

import com.yxxmg.rabbitmq.domain.SenderMessage;

import lombok.RequiredArgsConstructor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/7
 */
@Component
@RequiredArgsConstructor
public class QueueSenderFacade implements QueueSender {
    private final DelayedQueueSender delayedQueueSender;
    private final PriorityQueueSender priorityQueueSender;

    @Override
    public void send(SenderMessage senderMessage) {
        if (senderMessage.getDelayTime() < 0) {
            this.priorityQueueSender.send(senderMessage);
        } else {
            this.delayedQueueSender.send(senderMessage);
        }

    }
}
