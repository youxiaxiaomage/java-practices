package com.yxxmg.event.monitor;

import com.yxxmg.event.event.NotifyEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/6/6
 */
@Component
@Slf4j
public class NotifyMonitor {
    @Async
    // @EventListener
    @TransactionalEventListener(classes = NotifyEvent.class, fallbackExecution = true) // 事务
    public void notify(NotifyEvent notifyEvent) {
        log.info("notify msg: {}", notifyEvent.getSource());
    }
}
