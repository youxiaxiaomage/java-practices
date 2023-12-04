package com.yxxmg.springboot.samples.spi;

import java.util.List;

import org.springframework.core.io.support.SpringFactoriesLoader;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/2
 */
public class SpringSpiApplication {
    public static void main(String[] args) {
        List<DemoDao> demoDaos =
            SpringFactoriesLoader.loadFactories(DemoDao.class, SpringSpiApplication.class.getClassLoader());
        demoDaos.forEach(System.out::println);
        System.out.println("------------------------------");
        List<String> daoClassNames =
            SpringFactoriesLoader.loadFactoryNames(DemoDao.class, SpringSpiApplication.class.getClassLoader());
        daoClassNames.forEach(System.out::println);
    }
}
