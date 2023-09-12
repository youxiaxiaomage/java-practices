package com.yxxmg.plugins.datapermission;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.yxxmg.annotation.DataScope;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/10
 */
// @Signature 指定了type后 method以及args必须为指定type的方法参数顺序也要保持一致
// MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler
// MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, CacheKey cacheKey,
// BoundSql boundSql
@Intercepts({
    @Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
    @Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class,
            BoundSql.class})})
@Slf4j
// @Component
public class CustomInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取代理对象
        Object target = invocation.getTarget();
        // 代理方法
        Method method = invocation.getMethod();
        // 方法参数
        Object[] args = invocation.getArgs();
        MappedStatement statement = (MappedStatement)args[0];
        Object parameter = invocation.getArgs()[1];
        BoundSql boundSql = statement.getBoundSql(parameter);
        String originalSql = boundSql.getSql();
        Object parameterObject = boundSql.getParameterObject();
        // 判断是否开启了数据权限
        DataScope dataScope = isDataScope(statement);
        if (dataScope == null) {
            // 未开启
            return invocation.proceed();
        }
        // 这边可以
        String deptId = "2";
        String sql = condition(originalSql, deptId, dataScope);
        log.info("original sql :{}, final sql:{}", originalSql, sql);
        BoundSql newBoundSql =
            new BoundSql(statement.getConfiguration(), sql, boundSql.getParameterMappings(), parameterObject);
        MappedStatement newStatement = copyFromMappedStatement(statement, new BoundSqlSqlSource(newBoundSql));
        invocation.getArgs()[0] = newStatement;
        return invocation.proceed();
    }

    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder =
            new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.cache(ms.getCache());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }

    private String condition(String originalSql, String deptId, DataScope dataScope) {
        String field = "dept_id";
        if (StringUtils.isNotBlank(dataScope.alias())) {
            field = dataScope.alias() + ".dept_id";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(originalSql);
        int index = sb.indexOf("where");
        if (index < 0) {
            sb.append(" where ").append(field).append(" = ").append(deptId);
        } else {
            sb.insert(index + 5, " " + field + " = " + deptId + " and ");
        }
        return sb.toString();
    }

    private DataScope isDataScope(MappedStatement mappedStatement) {
        DataScope dataScope = null;
        try {
            String id = mappedStatement.getId();
            String className = id.substring(0, id.lastIndexOf("."));
            String methodName = id.substring(id.lastIndexOf(".") + 1);
            Class<?> clazz = Class.forName(className);
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodName) && method.isAnnotationPresent(DataScope.class)) {
                    dataScope = method.getAnnotation(DataScope.class);
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return dataScope;
    }

    public static class BoundSqlSqlSource implements SqlSource {

        private final BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

}
