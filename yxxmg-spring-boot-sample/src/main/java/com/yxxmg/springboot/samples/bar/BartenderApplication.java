package com.yxxmg.springboot.samples.bar;

import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/2
 */
public class BartenderApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(TavernConfiguration.class);
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        System.out.println("-------------------------------");
        Map<String, Bartender> beansOfType = applicationContext.getBeansOfType(Bartender.class);
        beansOfType.forEach((name, bartender) -> System.out.println(bartender));
    }
}
