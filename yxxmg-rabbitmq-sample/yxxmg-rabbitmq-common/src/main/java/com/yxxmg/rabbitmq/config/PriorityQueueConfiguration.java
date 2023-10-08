package com.yxxmg.rabbitmq.config;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

import com.google.common.collect.Maps;
import com.yxxmg.rabbitmq.enums.QueueEnum;
import com.yxxmg.rabbitmq.queue.PriorityQueue;

import lombok.AllArgsConstructor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :优先级队列配置类
 * @since : 2023/10/7
 */
@Configuration
public class PriorityQueueConfiguration {
    private static final String PRIORITY_QUEUE_BEAN_NAME_SUFFIX = ".priority.queue";
    private static final String PRIORITY_QUEUE_EXCHANGE_NAME_SUFFIX = ".priority.exchange";

    private static final String PRIORITY_BINDING_BEAN_NAME_SUFFIX = ".priority.binding.queue";

    @Resource
    private GenericApplicationContext genericApplicationContext;

    @Bean
    public List<PriorityQueue> priorityQueueList() {
        return Stream.of(QueueEnum.values()).map(this::createPriorityQueue).collect(Collectors.toList());
    }

    private PriorityQueue createPriorityQueue(QueueEnum queueEnum) {
        // 创建优先级队列
        createQueue(queueEnum);
        // 创建交换机
        createExchange(queueEnum);
        // 绑定
        createBinding(queueEnum);
        return new PriorityQueueWrapper(getExchangeName(queueEnum), queueEnum);
    }

    private void createBinding(QueueEnum queueEnum) {
        Binding binding =
            BindingBuilder.bind(this.genericApplicationContext.getBean(getQueueBeanName(queueEnum), Queue.class))
                .to(this.genericApplicationContext.getBean(getExchangeName(queueEnum), DirectExchange.class))
                .with(queueEnum.name());
        this.genericApplicationContext.registerBean(getBindingBeanName(queueEnum), Binding.class, () -> binding);
    }

    private static String getBindingBeanName(QueueEnum queue) {
        return queue.name() + PRIORITY_BINDING_BEAN_NAME_SUFFIX;
    }

    private String getExchangeName(QueueEnum queue) {
        return queue.name() + PRIORITY_QUEUE_EXCHANGE_NAME_SUFFIX;
    }

    private void createExchange(QueueEnum queue) {
        DirectExchange directExchange =
            new DirectExchange(queue.name() + PRIORITY_QUEUE_EXCHANGE_NAME_SUFFIX, true, false, Maps.newHashMap());
        this.genericApplicationContext.registerBean(getExchangeBeanName(queue), DirectExchange.class,
            () -> directExchange);
    }

    private String getExchangeBeanName(QueueEnum queue) {
        // WECHAT.priority.exchange
        return queue.name() + PRIORITY_QUEUE_EXCHANGE_NAME_SUFFIX;
    }

    private void createQueue(QueueEnum queueEnum) {
        Map<String, Object> args = Maps.newHashMap();
        args.put("x-max-priority", 5);
        Queue queue = new Queue(PriorityQueue.queueName(queueEnum), true, false, false, args);
        this.genericApplicationContext.registerBean(getQueueBeanName(queueEnum), Queue.class, () -> queue);
    }

    private static String getQueueBeanName(QueueEnum queueEnum) {
        return queueEnum.name() + PRIORITY_QUEUE_BEAN_NAME_SUFFIX;
    }

    @AllArgsConstructor
    static class PriorityQueueWrapper implements PriorityQueue {
        private final String exchangeName;
        private final QueueEnum queue;

        @Override
        public String exchangeName() {
            return exchangeName;
        }

        @Override
        public QueueEnum queue() {
            return queue;
        }
    }

}
