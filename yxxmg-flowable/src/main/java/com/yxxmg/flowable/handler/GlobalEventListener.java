package com.yxxmg.flowable.handler;

import org.flowable.common.engine.api.delegate.event.AbstractFlowableEventListener;
import org.flowable.common.engine.api.delegate.event.FlowableEngineEventType;
import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.engine.delegate.event.FlowableProcessStartedEvent;
import org.flowable.engine.delegate.event.impl.FlowableEntityEventImpl;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.flowable.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.flowable.engine.impl.util.CommandContextUtil;

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
        if (event.getType().equals(FlowableEngineEventType.PROCESS_STARTED)) {
            log.info("流程已启动");
            FlowableProcessStartedEvent flowableProcessStartedEvent = (FlowableProcessStartedEvent)event;
            System.out.println(flowableProcessStartedEvent);
            ExecutionEntityImpl executionEntity = (ExecutionEntityImpl)flowableProcessStartedEvent.getEntity();
            System.out.println(executionEntity);
            HistoricProcessInstanceEntity historicProcessInstanceCache = CommandContextUtil.getEntityCache()
                .findInCache(HistoricProcessInstanceEntity.class, executionEntity.getProcessInstanceId());
            System.out.println(historicProcessInstanceCache);

        }
        if (event.getType().equals(FlowableEngineEventType.PROCESS_CREATED)) {
            log.info("流程已创建");
            FlowableEntityEventImpl flowableEntityEvent = (FlowableEntityEventImpl)event;
            System.out.println(flowableEntityEvent);
            ExecutionEntityImpl executionEntity = (ExecutionEntityImpl)flowableEntityEvent.getEntity();
            System.out.println(executionEntity.getStartUserId());
        }
    }

    @Override
    public boolean isFailOnException() {
        return true;
    }

}
