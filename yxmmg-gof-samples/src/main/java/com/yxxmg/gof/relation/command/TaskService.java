package com.yxxmg.gof.relation.command;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/10
 */
public interface TaskService {
    /**
     * 获取Task
     * 
     * @return Task
     */
    Task newTask();

    /**
     * 获取Task
     * 
     * @param taskId String
     * @return Task
     */
    Task newTask(String taskId);
}
