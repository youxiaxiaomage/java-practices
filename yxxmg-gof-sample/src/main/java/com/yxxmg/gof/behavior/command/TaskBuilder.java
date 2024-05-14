package com.yxxmg.gof.behavior.command;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/10
 */
public interface TaskBuilder {
    /**
     * 获取Task
     * 
     * @return Task
     */
    Task create();

    /**
     * 获取TaskBuilder
     * 
     * @param id String
     * @return TaskBuilder
     */
    TaskBuilder id(String id);

    /**
     * 获取id
     * 
     * @return String
     */
    String getId();

    /**
     * 获取TaskBuilder
     * 
     * @param name String
     * @return TaskBuilder
     */
    TaskBuilder name(String name);

    /**
     * 获取name
     * 
     * @return String
     */
    String getName();
}
