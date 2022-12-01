package com.yxxmg.mybatisplussample.plugin;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2022/11/3
 */
@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface EnumApiModelProperty {
    boolean retCode() default true;
}
