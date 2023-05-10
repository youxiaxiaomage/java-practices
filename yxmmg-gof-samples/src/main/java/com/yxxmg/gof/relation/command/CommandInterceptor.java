package com.yxxmg.gof.relation.command;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO
 * @since : 2023/5/10
 */
public interface CommandInterceptor {
    <T> T execute(Command<T> command);

    void setNext(CommandInterceptor next);
}
