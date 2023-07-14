package com.yxxmg.gof.relation.command;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/10
 */
public class CustomCommandInterceptor implements CommandInterceptor {
    @Override
    public <T> T execute(Command<T> command) {
        return null;
    }

    @Override
    public void setNext(CommandInterceptor next) {

    }
}
