package com.yxxmg.flowable.callback;

import org.flowable.common.engine.impl.callback.CallbackData;
import org.flowable.common.engine.impl.callback.RuntimeInstanceStateChangeCallback;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 流程运行实例状态回调
 * @since : 2024/7/9
 */
@Slf4j
public class YxxmgRuntimeInstanceStateChangeCallback implements RuntimeInstanceStateChangeCallback {
    @Override
    public void stateChanged(CallbackData callbackData) {
        log.info("stateChanged old state {}, new state {}", callbackData.getOldState(), callbackData.getNewState());
    }
}
