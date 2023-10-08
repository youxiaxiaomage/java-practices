package com.yxxmg.rabbitmq.consumer;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yxxmg.rabbitmq.domain.SenderMessage;
import com.yxxmg.rabbitmq.enums.QueueEnum;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/8
 */
@Service
public class WechatConsumer implements MessageConsumer {
    @Override
    public QueueEnum queue() {
        return QueueEnum.WECHAT;
    }

    @Override
    public void consume(SenderMessage senderMessage) {
        System.out.println(queue() + "==>" + JSONObject.toJSONString(senderMessage));
    }
}
