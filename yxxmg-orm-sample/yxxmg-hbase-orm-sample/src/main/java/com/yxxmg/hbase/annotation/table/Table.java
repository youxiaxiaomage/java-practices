package com.yxxmg.hbase.annotation.table;

import java.lang.annotation.*;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/19
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Table {

    String namespace() default "default";

    String name();
}
