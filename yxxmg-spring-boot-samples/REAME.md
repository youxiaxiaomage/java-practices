| 注解                            | 说明               |      |
| ------------------------------- | ------------------ | ---- |
| `@ConditionalOnJava`            | `OnJavaCondition`  |      |
| `@ConditionalOnBean`            | `OnBeanCondition`  |      |
| `@ConditionalOnSingleCandidate` | `OnBeanCondition`  |      |
| `@ConditionalOnMissingBean`     | `OnBeanCondition`  |      |
| `@ConditionalOnMissingClass`    | `OnClassCondition` |      |
| `@ConditionalOnClass`           | `OnClassCondition` |      |

```java
org.springframework.boot.autoconfigure.condition.SpringBootCondition#getMatchOutcome
```

