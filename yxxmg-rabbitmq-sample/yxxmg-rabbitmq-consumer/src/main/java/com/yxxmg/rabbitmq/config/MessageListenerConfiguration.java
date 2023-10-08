package com.yxxmg.rabbitmq.config;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.yxxmg.rabbitmq.consumer.MessageConsumerFacade;
import com.yxxmg.rabbitmq.domain.SenderMessage;
import com.yxxmg.rabbitmq.enums.QueueEnum;
import com.yxxmg.rabbitmq.queue.DelayedQueue;
import com.yxxmg.rabbitmq.queue.PriorityQueue;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/8
 */
@Configuration
public class MessageListenerConfiguration {
    @Bean
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory,
        MessageConsumerFacade messageConsumerFacade) {
        DirectMessageListenerContainer container = new DirectMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueNames());
        container.setConsumersPerQueue(5);
        container.setPrefetchCount(1);
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setMessageListener(new CustomMessageListener(messageConsumerFacade));
        return container;
    }

    private String[] queueNames() {
        List<String> queueList = Lists.newArrayList();
        for (QueueEnum queueEnum : QueueEnum.values()) {
            queueList.add(DelayedQueue.queueName(queueEnum));
            queueList.add(PriorityQueue.queueName(queueEnum));
        }
        return queueList.toArray(new String[0]);
    }

    @Data
    static class CustomMessageListener implements MessageListener {
        private MessageConsumerFacade messageConsumerFacade;

        public CustomMessageListener(MessageConsumerFacade messageConsumerFacade) {
            this.messageConsumerFacade = messageConsumerFacade;
        }

        @Override
        public void onMessage(Message message) {
            this.messageConsumerFacade.consumer(
                JSONObject.parseObject(new String(message.getBody(), StandardCharsets.UTF_8), SenderMessage.class));
        }
    }
}
