package com.yxxmg.gof.behavior.command;

import lombok.RequiredArgsConstructor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : command上线文
 * @since : 2023/5/10
 */
@RequiredArgsConstructor
public class CommandContext {
    protected Command<?> command;
}
