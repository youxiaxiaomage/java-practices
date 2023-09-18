package com.yxxmg.spring.brick;

import com.gitee.starblues.loader.launcher.SpringBootstrap;
import com.gitee.starblues.loader.launcher.SpringMainBootstrap;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/17
 */
@SpringBootApplication
@MapperScan("com.yxxmg.spring.brick.mapper")
public class YxxmgBrickApplication implements SpringBootstrap {
    @Override
    public void run(String[] args) throws Exception {
        SpringApplication.run(YxxmgBrickApplication.class, args);
    }

    public static void main(String[] args) {
        SpringMainBootstrap.launch(YxxmgBrickApplication.class, args);
    }

//    @Override
//    public String developmentMode() {
//        return DevelopmentMode.ISOLATION;
//    }
}
