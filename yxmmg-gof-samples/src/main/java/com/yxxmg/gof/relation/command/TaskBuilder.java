package com.yxxmg.gof.relation.command;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/10
 */
public interface TaskBuilder {
    Task create();

    TaskBuilder id(String id);

    String getId();

    TaskBuilder name(String name);

    String getName();
}
