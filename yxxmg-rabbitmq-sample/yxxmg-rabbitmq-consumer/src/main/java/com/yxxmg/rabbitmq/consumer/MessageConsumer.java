package com.yxxmg.rabbitmq.consumer;

import com.yxxmg.rabbitmq.domain.SenderMessage;
import com.yxxmg.rabbitmq.enums.QueueEnum;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/8
 */
public interface MessageConsumer {
    QueueEnum queue();

    void consume(SenderMessage senderMessage);
}
