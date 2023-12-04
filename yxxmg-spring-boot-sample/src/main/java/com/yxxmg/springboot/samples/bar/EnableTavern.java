package com.yxxmg.springboot.samples.bar;

import java.lang.annotation.*;

import org.springframework.context.annotation.Import;

import com.yxxmg.springboot.samples.custom.Boss;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/2
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({Boss.class, BartenderConfiguration.class})
public @interface EnableTavern {}
