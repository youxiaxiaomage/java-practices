package com.yxxmg.gof.relation.command;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/10
 */
public interface CommandInterceptor {
    /**
     * 执行
     * 
     * @param command 命令
     * @param <T> 泛型
     * @return T
     */
    <T> T execute(Command<T> command);

    /**
     * 设置下一个解释器
     * 
     * @param next 下一个解释器
     */
    void setNext(CommandInterceptor next);
}
