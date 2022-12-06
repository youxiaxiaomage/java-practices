package com.yxxmg.flowable.handler;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO
 * @since : 2022/12/6
 */
public class BossHandler implements TaskListener {
    private static final long serialVersionUID = 465558378316953680L;

    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setAssignee("老板");
    }
}
