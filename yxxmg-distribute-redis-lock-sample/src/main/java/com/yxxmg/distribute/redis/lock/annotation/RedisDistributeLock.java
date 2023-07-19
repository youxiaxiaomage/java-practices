package com.yxxmg.distribute.redis.lock.annotation;

import java.lang.annotation.*;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/7/19
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisDistributeLock {
    String key() default "";

    long lockTime() default 5000;

    long releaseLock() default 5000;
}
