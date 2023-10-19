package com.yxxmg.hbase.annotation.table;

import java.lang.annotation.*;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/18
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Family {
    /**
     * 列簇名
     * 
     * @return 列簇名
     */
    String name();
}
