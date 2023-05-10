package com.yxxmg.gof.relation.command;

import java.io.Serializable;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO
 * @since : 2023/5/10
 */
public class NewTaskCmd implements Command<Task>, Serializable {
    private static final long serialVersionUID = 8219650046935515469L;
    protected String taskId;

    public NewTaskCmd(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public Task execute(CommandContext commandContext) {
        TaskEntity taskEntity = new TaskEntityImpl().createTask();
        taskEntity.setId(taskId);
        return taskEntity;
    }
}
