package com.yxxmg.hbase.binding;

import java.lang.reflect.Method;

import com.yxxmg.hbase.command.Command;
import com.yxxmg.hbase.command.HqlCommandType;
import com.yxxmg.hbase.config.Configuration;
import com.yxxmg.hbase.mapper.MappedStatement;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO
 * @since : 2023/10/20
 */
public class MapperMethod {
    private final Command command;

    public MapperMethod(Class<?> mapperInterface, Method method, Configuration configuration) {
        // this.command = new
    }

    public Object execute(Object[] args) {
        return null;
    }

    public static class HqlCommand {
        private final String name;
        private final HqlCommandType type;

        public HqlCommand(Configuration configuration, Class<?> mapperInterface, Method method) {
            final String methodName = method.getName();
            Class<?> declaringClass = method.getDeclaringClass();
            MappedStatement ms = resolveMappedStatement(mapperInterface, methodName, declaringClass, configuration);
            if (ms == null) {
                name = ms.getId();
                type = ms.getHqlCommandType();
            }
        }

        private MappedStatement resolveMappedStatement(Class<?> mapperInterface, String methodName,
            Class<?> declaringClass, Configuration configuration) {
            String statementId = mapperInterface.getName() + "." + methodName;
            if (configuration.hasStatement(statementId)) {
                return configuration.getMappedStatement(statementId);
            } else if (mapperInterface.equals(declaringClass)) {
                return null;
            }
            for (Class<?> superInterface : mapperInterface.getInterfaces()) {
                if (declaringClass.isAssignableFrom(superInterface)) {
                    MappedStatement ms =
                        resolveMappedStatement(superInterface, methodName, declaringClass, configuration);
                    if (ms != null) {
                        return ms;
                    }
                }
            }
            return null;
        }
    }
}
