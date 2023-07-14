package com.yxxmg.gof.relation.command;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/10
 */
public interface CommandExecutor {
    /**
     * 执行
     * 
     * @param command 命令
     * @param <T> 泛型
     * @return T
     */
    <T> T execute(Command<T> command);
}
