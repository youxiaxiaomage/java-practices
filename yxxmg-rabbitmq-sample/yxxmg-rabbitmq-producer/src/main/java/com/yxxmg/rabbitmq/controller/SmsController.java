package com.yxxmg.rabbitmq.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yxxmg.rabbitmq.dto.SmsDTO;
import com.yxxmg.rabbitmq.service.SmsService;

import lombok.RequiredArgsConstructor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/7
 */
@RestController
@RequestMapping("/sms")
@RequiredArgsConstructor
public class SmsController {
    private final SmsService smsService;

    @PostMapping("/send")
    public String send(@RequestBody @Validated SmsDTO smsDTO) {
        return this.smsService.send(smsDTO);
    }
}
