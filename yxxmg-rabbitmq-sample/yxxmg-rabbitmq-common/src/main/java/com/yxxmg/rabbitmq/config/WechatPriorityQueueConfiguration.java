package com.yxxmg.rabbitmq.config;

import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Maps;
import com.yxxmg.rabbitmq.enums.QueueEnum;
import com.yxxmg.rabbitmq.queue.PriorityQueue;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO
 * @since : 2023/10/7
 */
@Configuration
public class WechatPriorityQueueConfiguration implements PriorityQueue {
    private static final String PRIORITY_QUEUE_NAME = "wechat.priority.queue";

    @Bean("wechat.priority.queue")
    public Queue wechatPriorityQueue() {
        Map<String, Object> args = Maps.newHashMap();
        args.put("x-max-priority", 5);
        return new Queue(PRIORITY_QUEUE_NAME_PREFIX + QueueEnum.WECHAT.name(), true, false, false, args);
    }

    @Bean("wechat.priority.exchange")
    public DirectExchange priorityExchange() {
        return new DirectExchange(PRIORITY_QUEUE_NAME);
    }

    @Bean("wechat.priority.binding")
    public Binding bindingDelayedQueue(@Qualifier("wechat.priority.exchange") DirectExchange exchange,
        @Qualifier("wechat.priority.queue") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(QueueEnum.WECHAT.name());
    }

    @Override
    public String exchangeName() {
        return PRIORITY_QUEUE_NAME;
    }

    @Override
    public QueueEnum queue() {
        return QueueEnum.WECHAT;
    }
}
