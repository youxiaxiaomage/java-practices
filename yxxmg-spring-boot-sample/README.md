### 条件注解

| 注解                              | 说明                 |      |
|---------------------------------|--------------------|------|
| `@ConditionalOnJava`            | `OnJavaCondition`  |      |
| `@ConditionalOnBean`            | `OnBeanCondition`  |      |
| `@ConditionalOnSingleCandidate` | `OnBeanCondition`  |      |
| `@ConditionalOnMissingBean`     | `OnBeanCondition`  |      |
| `@ConditionalOnMissingClass`    | `OnClassCondition` |      |
| `@ConditionalOnClass`           | `OnClassCondition` |      |

```java
org.springframework.boot.autoconfigure.condition.SpringBootCondition#getMatchOutcome
```

### @Configuration之proxyBeanMethods

1. `proxyBeanMethods=true`
   `true`为`Full`全模式

   ```java
   test1
   test2
   com.yxxmg.springboot.samples.proxy.ProxyConfiguration$Test@69a10787
   16:13:37.697 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'test2'
   test2
   com.yxxmg.springboot.samples.proxy.ProxyConfiguration$Test@69b794e2
   com.yxxmg.springboot.samples.proxy.ProxyConfiguration$Test@3f200884
   ```

2. `proxyBeanMethods=false`
   `false`为`Lite`轻量级模式，不会被拦截进行CGLIB代理，不会走生命周期的Bean行为
   启动时不需要配置类生成CGLIB代理对象，减少启动时间

   ```java
   test1
   16:26:07.702 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'test2'
   test2
   com.yxxmg.springboot.samples.proxy.ProxyConfiguration$Test@3b07a0d6
   com.yxxmg.springboot.samples.proxy.ProxyConfiguration$Test@59d016c9
   com.yxxmg.springboot.samples.proxy.ProxyConfiguration$Test@3b07a0d6
   ```

### `DispatcherHandler`

