package com.yxxmg.event.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/6/6
 */
public class NotifyEvent extends ApplicationEvent {
    private static final long serialVersionUID = 5615947269632093413L;

    public NotifyEvent(Object source) {
        super(source);
    }
}
