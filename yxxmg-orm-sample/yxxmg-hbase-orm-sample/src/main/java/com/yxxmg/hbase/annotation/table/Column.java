package com.yxxmg.hbase.annotation.table;

import java.lang.annotation.*;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/18
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {
    /**
     * 列簇
     * 
     * @return 列簇
     */
    Family family();

    /**
     * 列名
     * 
     * @return 列名
     */
    String column();

    boolean exists() default true;
}
