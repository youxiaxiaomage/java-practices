# mybatis-plus团队分布式锁组件lock4j

## 介绍

lock4j是一个基于mybatis-plus的分布式锁组件，提供基于注解和基于接口的两种方式使用分布式锁。

## 快速开始

### 引入依赖

1. redisson版

```xml

<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>lock4j-redisson-spring-boot-starter</artifactId>
    <version>2.2.7</version>
</dependency>
```

2. redis版

```xml

<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>lock4j-redisson-spring-boot-starter</artifactId>
    <version>2.2.7</version>
</dependency>
```

3. zookeeper版

```xml

<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>lock4j-redisson-spring-boot-starter</artifactId>
    <version>2.2.7</version>
</dependency>
```