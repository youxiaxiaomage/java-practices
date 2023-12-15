package com.yxxmg.event.monitor;

import com.yxxmg.event.event.NotifyEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/15
 */
@Slf4j
public class NotifyApplicationListener implements ApplicationListener<NotifyEvent> {
    @Override
    public void onApplicationEvent(NotifyEvent event) {
        log.info(event.getMsg());
    }
}
