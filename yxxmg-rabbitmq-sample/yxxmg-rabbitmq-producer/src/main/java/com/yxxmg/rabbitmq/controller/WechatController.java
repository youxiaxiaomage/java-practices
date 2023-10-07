package com.yxxmg.rabbitmq.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yxxmg.rabbitmq.dto.WechatDTO;
import com.yxxmg.rabbitmq.service.WechatService;

import lombok.RequiredArgsConstructor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/7
 */
@RestController
@RequestMapping("/wechat")
@RequiredArgsConstructor
public class WechatController {
    private final WechatService wechatService;

    @PostMapping("/send")
    public String send(@RequestBody @Validated WechatDTO wechatDTO) {
        return this.wechatService.send(wechatDTO);
    }
}
