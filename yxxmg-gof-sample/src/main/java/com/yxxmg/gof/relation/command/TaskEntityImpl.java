package com.yxxmg.gof.relation.command;

import java.io.Serializable;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/10
 */
public class TaskEntityImpl implements TaskEntity, Serializable {
    private static final long serialVersionUID = 7486224896583331291L;

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void setId(String id) {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getName() {
        return null;
    }

    public TaskEntity createTask() {
        return new TaskEntityImpl();
    }
}
