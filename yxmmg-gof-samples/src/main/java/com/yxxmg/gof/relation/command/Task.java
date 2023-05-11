package com.yxxmg.gof.relation.command;

import java.io.Serializable;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/10
 */
public interface Task {
    /**
     * 获取任务主键
     * 
     * @return String
     */
    String getId();

    /**
     * 设置主键
     * 
     * @param id String
     */
    void setId(String id);

    /**
     * 设置名称
     * 
     * @param name String
     */
    void setName(String name);

    /**
     * 获取名称
     * 
     * @return String
     */
    String getName();
}
