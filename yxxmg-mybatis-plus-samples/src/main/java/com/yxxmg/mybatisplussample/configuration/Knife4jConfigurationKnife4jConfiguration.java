package com.yxxmg.mybatisplussample.configuration;

import com.yxxmg.mybatisplussample.plugin.EnumModelPropertyBuilderPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : knife4j
 * @since : 2022/11/3
 */
@Configuration
@EnableOpenApi
public class Knife4jConfigurationKnife4jConfiguration {
    @Bean
    @Order
    public EnumModelPropertyBuilderPlugin enumModelPropertyBuilderPlugin() {
        return new EnumModelPropertyBuilderPlugin();
    }

    @Bean("dockerBean")
    public Docket dockerBean() {
        return new Docket(DocumentationType.OAS_30)
            .apiInfo(new ApiInfoBuilder().description("Knife4j test").termsOfServiceUrl("http://xxxx")
                .contact(new Contact("yxxmg", "http://xxx", "xxx")).version("1.0").build())
            .groupName("测试服务").select().apis(RequestHandlerSelectors.basePackage("com.yxxmg.mybatisplussample"))
            .paths(PathSelectors.any()).build();
    }
}
