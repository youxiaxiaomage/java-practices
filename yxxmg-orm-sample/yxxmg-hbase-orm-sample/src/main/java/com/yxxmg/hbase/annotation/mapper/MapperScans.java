package com.yxxmg.hbase.annotation.mapper;

import java.lang.annotation.*;

import org.springframework.context.annotation.Import;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/19
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MapperScannerRegistrar.RepeatingRegistrar.class)
public @interface MapperScans {
    MapperScan[] value();
}
