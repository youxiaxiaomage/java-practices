package com.yxxmg.rabbitmq.queue;

import com.yxxmg.rabbitmq.enums.QueueEnum;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/7
 */
public interface DelayedQueue {
    /**
     * 声明队列类型
     */

    String DELAY_QUEUE_EXCHANGE_TYPE = "x-delayed-message";
    /**
     * 声明队列名称前缀
     */
    String DELAY_QUEUE_NAME_PREFIX = "delayed.";

    /**
     * 交换器名称
     * 
     * @return 交换器名称
     */
    String exchangeName();

    /**
     * 返回队列名称
     * 
     * @param queue 队列
     * @return 返回队列名称
     */
    static String queueName(QueueEnum queue) {
        return DELAY_QUEUE_NAME_PREFIX + queue.name();
    }

    /**
     * 队列枚举
     * 
     * @return 返回队列枚举
     */
    QueueEnum queue();
}