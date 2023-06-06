package com.yxxmg.event.controller;

import com.yxxmg.event.service.NotifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/6/6
 */
@RestController
@RequiredArgsConstructor
public class SampleController {
    private final NotifyService notifyService;

    public String notify(String msg) {
        return this.notifyService.notify(msg);
    }
}
