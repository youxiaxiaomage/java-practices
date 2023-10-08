#### `redis`实现分布式锁

```java

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisDistributeLock {
    String key() default "";

    long lockTime() default 5000;

    long releaseLock() default 5000;
}
```

```java
Component

@Slf4j
@RequiredArgsConstructor
@Aspect
public class RedisDistributeLockAspect {
    private final RedissonClientDelegate redissonClientDelegate;

    @Pointcut("@annotation(com.yxxmg.distribute.redis.lock.annotation.RedisDistributeLock)")
    public void pointCut() {
    }

    public Object proceed(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        RedisDistributeLock redisDistributeLock = getAnnotation(proceedingJoinPoint);
        String valueKey =
                generateDistributeLockKey(proceedingJoinPoint, redisDistributeLock, proceedingJoinPoint.getArgs());
        try (CloseableRLock lock = this.redissonClientDelegate.getFairLock(valueKey)) {
            if (lock.tryLock(redisDistributeLock.lockTime(), redisDistributeLock.releaseLock(),
                    TimeUnit.MILLISECONDS)) {
                return proceedingJoinPoint.proceed();
            } else {
                throw new IllegalArgumentException("current thread get lock failed");
            }
        }
    }

    private static RedisDistributeLock getAnnotation(ProceedingJoinPoint proceedingJoinPoint) {
        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        return method.getAnnotation(RedisDistributeLock.class);
    }

    private String generateDistributeLockKey(ProceedingJoinPoint proceedingJoinPoint,
                                             RedisDistributeLock redisDistributeLock, Object[] args) {
        LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer =
                new LocalVariableTableParameterNameDiscoverer();
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
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
```

### 开发使用到的