package com.yxxmg.flowable.interceptor;

import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.flowable.engine.interceptor.IdentityLinkInterceptor;
import org.flowable.identitylink.service.impl.persistence.entity.IdentityLinkEntity;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/7/9
 */
public class YxxmgIdentityLinkInterceptor implements IdentityLinkInterceptor {
    @Override
    public void handleCompleteTask(TaskEntity task) {
        // complete

    }

    @Override
    public void handleAddIdentityLinkToTask(TaskEntity taskEntity, IdentityLinkEntity identityLinkEntity) {
        // add
    }

    @Override
    public void handleAddAssigneeIdentityLinkToTask(TaskEntity taskEntity, String assignee) {
        // assignee
    }

    @Override
    public void handleAddOwnerIdentityLinkToTask(TaskEntity taskEntity, String owner) {
        // owner
    }

    @Override
    public void handleCreateProcessInstance(ExecutionEntity processInstanceExecution) {
        // create
    }

    @Override
    public void handleCreateSubProcessInstance(ExecutionEntity subProcessInstanceExecution,
        ExecutionEntity superExecution) {
        // create sub process
    }
}
