<p align="center">
    <img src="images/logo.png" width="150">
    <p align="center">
    	java-practices日常开发中的开发工具，编码技巧以及设计思想等，持续更新中
    	<br>
    	<a href="http://www.apache.org/licenses/LICENSE-2.0.html">
      		<img src="http://img.shields.io/:license-apache-blue.svg" >
   		</a>
   		<a href="https://www.oracle.com/technetwork/java/javase/downloads">
      		<img src="https://img.shields.io/badge/JDK-1.8-green.svg" >
   		</a>        	  
    	<a href="https://gitee.com/youxiaxiaomage/java-practices/releases">
       		<img src="https://img.shields.io/badge/1.0.0-brightgreen.svg" >
     	</a>
    </p>    
</p>

| 目录                                                         | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [`(有链接)yxxmg-mybatis-plus-sample`](./yxxmg-mybatis-plus-sample/readme.md) | 动态表名，`Mybatis-Plus`自定义枚举,`Mapstruct`,`JSR303`,`JSR330`，`Caffeine`,`Knife4j`,`Openfegin`,`JustAuth`,`Easy-Excel`,`Easy-ES` |
| `yxxmg-mybatis-flex-sample`                                  | `mybatis-flex`新的`orm`框架，目前是`1.6.3`版本（目前版本可能存在问题，当前版本也是紧急修复的版本） |
| [`(有链接)yxxmg-pay-spring-boot-starter`](./yxxmg-pay-spring-boot-starter/readme.md) | `springboot`自动装配，`SPI`自定义支付`starter`               |
| `yxxmg-drools-sample`                                        | 规则引擎                                                     |
| `yxxmg-java-helper`                                          | `java`语法糖                                                 |
| `yxxmg-spring-boot-sample`                                   | `springboot`相关内容                                         |
| `yxxmg-flowable`                                             | `flowable`工作流引擎                                         |
| `yxxmg-elasticjob`                                           | `elasticjob`任务调度                                         |
| `yxxmg-smart-doc`                                            | `smart-doc`                                                  |
| `yxxmg-exception-sample`                                     | 自定义异常或国际化                                           |
| [`(有链接)yxxmg-sensitive-sample`](./yxxmg-sensitive-sample/readme.md) | `jackson`序列化与反序列化，例如字段脱敏，字符串去除首尾空格等 |
| `yxxmg-liteflow-sample`                                      | `liteflow`服务编排                                           |
| `yxxmg-magic-api-sample`                                     | `magic-api`                                                  |
| `yxxmg-event-sample`                                         | `ApplicationEvent`，`ApplicationEventPublisher`              |
| `yxxmg-javers-sample`                                        | 对象前后变化对比（未开始）                                   |
| `yxxmg-oos-sample`                                           | 阿里云`oos`                                                  |
| `yxxmg-nlp-sample`                                           | `Stanford nlp`                                               |
| `yxxmg-magic-api-sample`                                     | `magic-api`                                                  |
| `yxxmg-gof-sample`                                           | `Gof`设计模式                                                |
| [`(有链接)yxxmg-distribute-redis-lock-sample`](./yxxmg-distribute-redis-lock-sample/readme.md) | `redis`实现分布式锁                                          |
| `yxxmg-sa-token-sample`                                      | `sa-token`功能                                               |
| [`(有链接)yxxmg-dynamic-feign-sample`](./yxxmg-dynamic-feign-sample/readme.md) | 动态`feign`                                                  |
| `yxxmg-okhttp-sample`                                        | `okhttp3`                                                    |
| [`(有链接)yxxmg-mybatis-spring-sample`](./yxxmg-mybatis-spring-sample/readme_zh.md) | `mybatis`基础研究                                            |
| `yxxmg-rabbitmq-sample`                                      | 对生产者消费者进行了对应的`工厂模式+策略模式`的封装，扩展性极强, 动态创建`Queue`,`Exchange`，`Binding` |

#### 开发工具Idea

#### 开发中常用插件

1. lombok 简化代码，一般idea会集成，有些版本不自带，需要手动配置
2. MybatisX mybatis快速开发定位
3. Save Actions 自动保存，以及格式化代码需要配合代码风格插件使用
4. Code for Eclipse Code Formater eclipse代码风格
5. Maven Helper 用于Maven管理
6. Arthas Idea Plugin 性能工具
7. Key Promoter X 快捷键
8. Rainbow Brackets 代码括号彩虹色，方便看代码
9. Alibaba Java Coding Guidelines 阿里代码检视
10. CamelCase 驼峰命名变量 方法 类等
11. GitToolBox Git工具，方便提交更新代码等
12. RestfulToolKit 方便定位接口
13. Yapi 接口管理类似Knife4j
14. MeterSphere 接口自动化
15. Jenkins 流水线发版
16. xxl-job 分布式任务调度(light-task-scheduler)
17. Sonar Lint 代码漏洞
18. Confluence 文档管理 内部文档的传递

##### 使用的中间件

1. rabbitmq
2. activemq
3. kafka
4. elasticsearch
5. hbase
6. redis数据缓存以及基于Redisson实现的分布式锁

##### 多环境打包

1. pom.xml配置

   ```xml
       <build>
           <plugins>
               <plugin>
                   <groupId>org.apache.maven.plugins</groupId>
                   <artifactId>maven-compiler-plugin</artifactId>
                   <configuration>
                       <source>1.8</source>
                       <target>1.8</target>
                   </configuration>
               </plugin>
               <plugin>
                   <groupId>org.springframework.boot</groupId>
                   <artifactId>spring-boot-maven-plugin</artifactId>
                   <version>2.7.5</version>
                   <configuration>
                       <fork>true</fork>
                   </configuration>
                   <executions>
                       <execution>
                           <goals>
                               <goal>repackage</goal>
                           </goals>
                       </execution>
                   </executions>
               </plugin>
               <plugin>
                   <groupId>org.apache.maven.plugins</groupId>
                   <artifactId>maven-war-plugin</artifactId>
                   <version>3.3.2</version>
                   <configuration>
                       <failOnMissingWebXml>false</failOnMissingWebXml>
                       <warName>${project.artifactId}</warName>
                   </configuration>
               </plugin>
           </plugins>
           <finalName>${project.artifactId}</finalName>
       </build>
       <profiles>
           <profile>
               <id>dev</id>
               <properties>
                   <spring.profiles.active>dev</spring.profiles.active>
               </properties>
               <activation>
                   <activeByDefault>true</activeByDefault>
               </activation>
           </profile>
           <profile>
               <id>prod</id>
               <properties>
                   <spring.profiles.active>prod</spring.profiles.active>
               </properties>
           </profile>
       </profiles>
   ```

2. application.yml配置

   ```yaml
   spring:
     profiles:
       active: @spring.profiles.active@
   ```

3. maven打包命令

   ```yaml
   # 测试环境打包，默认方式dev
   mvn clean package -P dev或mvn clean package
   # 生产环境打包
   mvn clean package -P prod
   ```

