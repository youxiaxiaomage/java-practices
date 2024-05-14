package com.yxxmg.gof.behavior.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/10
 */
public abstract class AbstractProcessEngineConfigurationImpl extends AbstractEngineConfiguration {
    protected CommandExecutor commandExecutor;
    protected List<CommandInterceptor> commandInterceptors;

    public void initCommandExecutor() {
        if (commandExecutor == null) {
            CommandInterceptor first = initInterceptorChain(commandInterceptors);
            commandExecutor = new CommandExecutorImpl(first);
        }

    }

    public void initCommandInterceptors() {
        if (commandInterceptors == null) {
            commandInterceptors = Lists.newArrayList();
            commandInterceptors.addAll(getDefaultCommandInterceptors());
        }
    }

    private List<CommandInterceptor> getDefaultCommandInterceptors() {
        return Stream.of(new SpringCommandInterceptor(), new CustomCommandInterceptor()).collect(Collectors.toList());
    }

    private CommandInterceptor initInterceptorChain(List<CommandInterceptor> chain) {
        if (chain == null || chain.isEmpty()) {
            throw new RuntimeException("invalid command interceptor chain configuration:" + chain);
        }
        for (int i = 0; i < chain.size() - 1; i++) {
            chain.get(i).setNext(chain.get(i + 1));

        }
        return chain.get(0);
    }
}
