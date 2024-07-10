package com.yxxmg.flowable.interceptor;

import org.flowable.engine.impl.interceptor.DefaultIdentityLinkInterceptor;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.flowable.identitylink.service.impl.persistence.entity.IdentityLinkEntity;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/7/9
 */
public class YxxmgIdentityLinkInterceptor extends DefaultIdentityLinkInterceptor {
    @Override
    public void handleCompleteTask(TaskEntity task) {
        // complete
        super.handleCompleteTask(task);
    }

    @Override
    public void handleAddIdentityLinkToTask(TaskEntity taskEntity, IdentityLinkEntity identityLinkEntity) {
        // add
        super.handleAddIdentityLinkToTask(taskEntity, identityLinkEntity);
    }

    @Override
    public void handleAddAssigneeIdentityLinkToTask(TaskEntity taskEntity, String assignee) {
        // assignee
        super.handleAddAssigneeIdentityLinkToTask(taskEntity, assignee);
    }

    @Override
    public void handleAddOwnerIdentityLinkToTask(TaskEntity taskEntity, String owner) {
        // owner
        super.handleAddOwnerIdentityLinkToTask(taskEntity, owner);
    }

    @Override
    public void handleCreateProcessInstance(ExecutionEntity processInstanceExecution) {
        // create
        super.handleCreateProcessInstance(processInstanceExecution);
    }

    @Override
    public void handleCreateSubProcessInstance(ExecutionEntity subProcessInstanceExecution,
        ExecutionEntity superExecution) {
        // create sub process
        super.handleCreateSubProcessInstance(subProcessInstanceExecution, superExecution);
    }
}
