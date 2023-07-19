package com.yxxmg.gof.relation.command;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/10
 */
public interface Command<T> {
    /**
     * 执行
     * 
     * @param commandContext 上线文
     * @return T
     */
    T execute(CommandContext commandContext);
}
