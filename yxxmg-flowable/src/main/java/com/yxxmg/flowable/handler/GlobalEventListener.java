package com.yxxmg.flowable.handler;

import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;
import org.springframework.stereotype.Component;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 全局事件监听器
 * @since : 2023/7/14
 */
@Component
public class GlobalEventListener implements FlowableEventListener {
    @Override
    public void onEvent(FlowableEvent event) {
        // TODO 具体的监听事件 比如任务创建 可以发送短信提醒 任务完成推送日志等
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }

    @Override
    public boolean isFireOnTransactionLifecycleEvent() {
        return false;
    }

    @Override
    public String getOnTransaction() {
        return null;
    }
}
