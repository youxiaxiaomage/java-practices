package com.yxxmg.event.service;

import com.yxxmg.event.event.NotifyEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/6/6
 */
@Service
@RequiredArgsConstructor
public class NotifyService {
    private final ApplicationEventPublisher applicationEventPublisher;

    public String notify(String msg) {
        this.applicationEventPublisher.publishEvent(new NotifyEvent(this, msg));
        return "success";
    }
}
