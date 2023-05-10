package com.yxxmg.gof.relation.command;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/10
 */
public abstract class CommonServiceImpl<C> {

    protected C configuration;

    public CommonServiceImpl() {}

    public C getConfiguration() {
        return configuration;
    }

    public CommonServiceImpl(C configuration) {
        this.configuration = configuration;
    }
}
