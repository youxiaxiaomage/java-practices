package com.yxxmg.gof.relation.command;

import java.io.Serializable;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO
 * @since : 2023/5/10
 */
public interface Task {
    String getId();

    void setId(String id);

    void setName(String name);

    String getName();
}
