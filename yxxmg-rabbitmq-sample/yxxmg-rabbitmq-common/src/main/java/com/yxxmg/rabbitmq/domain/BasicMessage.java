package com.yxxmg.rabbitmq.domain;

import java.io.Serializable;

import com.yxxmg.rabbitmq.enums.QueueEnum;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/7
 */
@Data
public class BasicMessage implements Serializable {
    private String message;
    private Integer priority;
    private Integer delayTime;

    public static SenderMessage to(QueueEnum queue, BasicMessage basicMessage) {
        return SenderMessage.builder().queue(queue).message(basicMessage.getMessage())
            .priority(basicMessage.getPriority()).delayTime(basicMessage.getDelayTime()).build();
    }
}
