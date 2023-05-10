package com.yxxmg.gof.relation.command;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO
 * @since : 2023/5/10
 */
public interface TaskService {
    Task newTask();

    Task newTask(String taskId);
}
