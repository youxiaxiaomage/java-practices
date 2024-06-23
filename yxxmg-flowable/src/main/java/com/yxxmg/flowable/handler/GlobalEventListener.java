package com.yxxmg.flowable.handler;

import org.flowable.common.engine.api.delegate.event.AbstractFlowableEventListener;
import org.flowable.common.engine.api.delegate.event.FlowableEngineEventType;
import org.flowable.common.engine.api.delegate.event.FlowableEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 全局事件监听器
 * @since : 2023/7/14
 */
@Slf4j
public class GlobalEventListener extends AbstractFlowableEventListener {
    @Override
    public void onEvent(FlowableEvent event) {
        if (event.getType().equals(FlowableEngineEventType.TASK_CREATED)) {
            //
            log.info("任务创建待办任务");
        }
        if (event.getType().equals(FlowableEngineEventType.TASK_COMPLETED)) {
            log.info("任务创建已办任务");
        }
        if (event.getType().equals(FlowableEngineEventType.PROCESS_COMPLETED)) {
            log.info("流程已完结");
        }
    }

    @Override
    public boolean isFailOnException() {
        return true;
    }

}
