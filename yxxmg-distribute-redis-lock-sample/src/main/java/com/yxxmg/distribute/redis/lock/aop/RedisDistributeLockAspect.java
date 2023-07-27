package com.yxxmg.distribute.redis.lock.aop;

import com.yxxmg.distribute.redis.lock.annotation.RedisDistributeLock;
import com.yxxmg.distribute.redis.lock.config.CloseableRLock;
import com.yxxmg.distribute.redis.lock.config.RedissonClientDelegate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO
 * @since : 2023/7/19
 */
@Component
@Slf4j
@RequiredArgsConstructor
@Aspect
public class RedisDistributeLockAspect {
    private final RedissonClientDelegate redissonClientDelegate;

    @Pointcut("@annotation(com.yxxmg.distribute.redis.lock.annotation.RedisDistributeLock)")
    public void pointCut() {}

    public Object proceed(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        RedisDistributeLock redisDistributeLock = getAnnotation(proceedingJoinPoint);
        String valueKey =
            generateDistributeLockKey(proceedingJoinPoint, redisDistributeLock, proceedingJoinPoint.getArgs());
        try (CloseableRLock lock = this.redissonClientDelegate.getFairLock(valueKey)) {
            if (lock.tryLock(redisDistributeLock.lockTime(), redisDistributeLock.releaseLock(),
                TimeUnit.MILLISECONDS)) {
                return proceedingJoinPoint.proceed();
            }
        }
        throw new IllegalArgumentException("current thread get lock failed");
    }

    private static RedisDistributeLock getAnnotation(ProceedingJoinPoint proceedingJoinPoint) {
        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method method = methodSignature.getMethod();
        return method.getAnnotation(RedisDistributeLock.class);
    }

    private String generateDistributeLockKey(ProceedingJoinPoint proceedingJoinPoint,
        RedisDistributeLock redisDistributeLock, Object[] args) {
        LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer =
            new LocalVariableTableParameterNameDiscoverer();
        MethodSignature signature = (MethodSignature)proceedingJoinPoint.getSignature();
        String[] parameterNames = localVariableTableParameterNameDiscoverer.getParameterNames(signature.getMethod());
        if (ObjectUtils.isEmpty(parameterNames)) {
            throw new IllegalArgumentException("redis distribute lock must have key param");
        }
        ExpressionParser expressionParser = new SpelExpressionParser();
        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
        for (int i = 0; i < parameterNames.length; i++) {
            standardEvaluationContext.setVariable(parameterNames[i], args[i]);
        }
        try {
            return expressionParser.parseExpression(redisDistributeLock.key()).getValue(standardEvaluationContext,
                String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
