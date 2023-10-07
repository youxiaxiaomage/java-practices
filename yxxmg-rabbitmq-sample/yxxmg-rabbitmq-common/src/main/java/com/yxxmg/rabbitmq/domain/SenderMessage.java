package com.yxxmg.rabbitmq.domain;

import java.io.Serializable;

import com.yxxmg.rabbitmq.enums.QueueEnum;

import lombok.Builder;
import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/7
 */
@Data
@Builder
public class SenderMessage implements Serializable {
    // 消息内容
    private String message;
    // 延迟时间
    private Integer delayTime = -1;
    // 队列
    private QueueEnum queue;
    private Integer priority;
}