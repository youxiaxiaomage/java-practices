package com.yxxmg.annotation;

import java.lang.annotation.*;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 自定义注解类
 * @since : 2023/9/10
 */
// 方法注解
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface DataScope {

    /**
     * 表字段别名
     * 
     * @return 表别名
     */
    String alias() default "";

    /**
     * 列名
     * 
     * @return 字段列
     */
    String column() default "";
}
