package com.yxxmg.springboot.samples.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.system.JavaVersion;
import org.springframework.stereotype.Service;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2022/12/5
 */
@Service
/* Parameter 0 of constructor in com.yxxmg.springboot.samples.controller.TestController required a bean of type
 'com.yxxmg.springboot.samples.service.TestService' that could not be found.
 @ConditionalOnClass(name = "com.yxxmg.springboot.samples.service.TestService")
 @ConditionalOnMissingClass
 @ConditionalOnMissingBean
 @ConditionalOnSingleCandidate*/
@ConditionalOnBean(name = "userService")
@ConditionalOnJava(range = ConditionalOnJava.Range.EQUAL_OR_NEWER, value = JavaVersion.EIGHT)
public class TestService {
    public String test(String str) {
        System.out.println(str);
        return str;
    }
}
