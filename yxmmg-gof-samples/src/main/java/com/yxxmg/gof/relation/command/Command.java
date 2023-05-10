package com.yxxmg.gof.relation.command;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO
 * @since : 2023/5/10
 */
public interface Command<T> {
    T execute(CommandContext commandContext);
}
