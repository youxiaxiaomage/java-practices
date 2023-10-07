package com.yxxmg.spring.extension;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : ContextStartedEvent ContextStoppedEvent ContextClosedEvent RequestHandledEvent
 * @since : 2023/10/7
 */
@Component
public class TestApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("TestApplicationListener.onApplicationEvent ContextRefreshedEvent");
    }
}
