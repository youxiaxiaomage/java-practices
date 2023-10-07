package com.yxxmg.rabbitmq.service;

import org.springframework.stereotype.Service;

import com.yxxmg.rabbitmq.dto.WechatDTO;
import com.yxxmg.rabbitmq.enums.QueueEnum;
import com.yxxmg.rabbitmq.queue.QueueSenderFacade;

import lombok.RequiredArgsConstructor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/7
 */
@Service
@RequiredArgsConstructor
public class WechatService {
    private final QueueSenderFacade queueSenderFacade;

    public String send(WechatDTO wechatDTO) {
        this.queueSenderFacade.send(WechatDTO.to(QueueEnum.WECHAT, wechatDTO));
        return "send success";
    }
}
