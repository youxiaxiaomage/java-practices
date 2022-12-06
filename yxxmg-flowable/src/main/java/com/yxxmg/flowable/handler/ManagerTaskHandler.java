package com.yxxmg.flowable.handler;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2022/12/6
 */
public class ManagerTaskHandler implements TaskListener {

    private static final long serialVersionUID = -5013872193111748279L;

    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setAssignee("经理");
    }
}
