package com.yxxmg.gof.behavior.command;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/10
 */
public abstract class AbstractCommonServiceImpl<C> {

    protected C configuration;

    public AbstractCommonServiceImpl() {}

    public C getConfiguration() {
        return configuration;
    }

    public AbstractCommonServiceImpl(C configuration) {
        this.configuration = configuration;
    }
}
