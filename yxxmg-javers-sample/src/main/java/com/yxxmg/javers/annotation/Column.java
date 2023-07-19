package com.yxxmg.javers.annotation;

import java.lang.annotation.*;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/6/12
 */
@Target(ElementType.FIELD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    /**
     * 列名
     * 
     * @return 名称
     */
    String name();

    /**
     * 展示顺序
     * 
     * @return -1不展示顺序,其余按照配置展示
     */
    int order() default -1;

    boolean isCollection() default false;
}
