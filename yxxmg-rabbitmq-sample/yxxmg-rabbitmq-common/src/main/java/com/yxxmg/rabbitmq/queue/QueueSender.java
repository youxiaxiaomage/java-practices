package com.yxxmg.rabbitmq.queue;

import com.yxxmg.rabbitmq.domain.SenderMessage;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 队列发送者
 * @since : 2023/10/7
 */
public interface QueueSender {
    /**
     *
     * 发送
     * 
     * @param senderMessage 消息
     */
    void send(SenderMessage senderMessage);

}
