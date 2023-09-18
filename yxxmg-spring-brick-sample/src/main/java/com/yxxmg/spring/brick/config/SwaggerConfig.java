package com.yxxmg.spring.brick.config;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.function.Predicate;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/17
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {
    @Value("${server.port}")
    private String port;


    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Predicate<String> predicate = PathSelectors.any();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("dev")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(predicate)
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档")
                .description("接口说明文档")
                .termsOfServiceUrl("http://ip:" + port + "/**")
                .contact(new Contact("yxxmg", "", ""))
                .version("1.0.0-SNAPSHOT")
                .build();
    }


}
