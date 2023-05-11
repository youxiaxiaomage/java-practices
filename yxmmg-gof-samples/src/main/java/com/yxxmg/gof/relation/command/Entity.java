package com.yxxmg.gof.relation.command;

import java.io.Serializable;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/10
 */
public interface Entity {
    /**
     * 获取主键
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
}
