package com.yxxmg.springboot.samples.custom;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/2
 */
public class TavernApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(TavernConfiguration.class);
        Boss boss = applicationContext.getBean(Boss.class);
        System.out.println(boss);
    }
}
