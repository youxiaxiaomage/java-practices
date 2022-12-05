package com.yxxmg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2022/12/5
 */
@SpringBootApplication
public class YxxmgFlowableApplication {
    public static void main(String[] args) {
        SpringApplication.run(YxxmgFlowableApplication.class, args);
    }

    // @Bean
    // public CommandLineRunner init(final RepositoryService repositoryService, final RuntimeService runtimeService,
    // final TaskService taskService) {
    //
    // return strings -> {
    // System.out
    // .println("Number of process definitions : " + repositoryService.createProcessDefinitionQuery().count());
    // System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
    // runtimeService.startProcessInstanceByKey("oneTaskProcess");
    // System.out.println("Number of tasks after process start: " + taskService.createTaskQuery().count());
    // };
    // }
}
