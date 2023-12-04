package com.yxxmg.springboot.samples.custom;

import java.lang.annotation.*;

import org.springframework.context.annotation.Import;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/2
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(Boss.class)
public @interface EnableTavern {}
