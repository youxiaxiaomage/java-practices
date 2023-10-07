package com.yxxmg.rabbitmq.queue;

import com.yxxmg.rabbitmq.enums.QueueEnum;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 优先级队列
 * @since : 2023/10/7
 */
public interface PriorityQueue {
    String PRIORITY_QUEUE_NAME_PREFIX = "priority.";

    /**
     * 交换机名称
     * 
     * @return 交换机名称
     */
    String exchangeName();

    /**
     * 队列名称
     * 
     * @param queue 队列
     * @return 队列名称
     */
    static String queueName(QueueEnum queue) {
        return PRIORITY_QUEUE_NAME_PREFIX + queue.name();
    }

    /**
     * 队列
     * 
     * @return 队列
     */
    QueueEnum queue();
}
