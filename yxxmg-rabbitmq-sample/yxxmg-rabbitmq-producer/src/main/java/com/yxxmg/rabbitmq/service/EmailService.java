package com.yxxmg.rabbitmq.service;

import org.springframework.stereotype.Service;

import com.yxxmg.rabbitmq.dto.EmailDTO;
import com.yxxmg.rabbitmq.dto.SmsDTO;
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
public class EmailService {
    private final QueueSenderFacade queueSenderFacade;

    public String send(EmailDTO emailDTO) {
        this.queueSenderFacade.send(SmsDTO.to(QueueEnum.EMAIL, emailDTO));
        return "send success";
    }
}
