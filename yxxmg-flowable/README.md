# flowable6.8.0

## 自定义Id生成器

```java
public class YxxmgIdGenerator implements IdGenerator {
    @Override
    public String getNextId() {
        return new Snowflake().nextIdStr();
    }
}
```

```java
@Configuration
public class FlowableConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {

    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        springProcessEngineConfiguration.setIdGenerator(new YxxmgIdGenerator());  
    }
}
```

## 自定义创建用户任务拦截器

```java
@Slf4j
public class YxxmgCreateUserTaskInterceptor implements CreateUserTaskInterceptor {
    @Override
    public void beforeCreateUserTask(CreateUserTaskBeforeContext context) {
        log.info("beforeCreateUserTask do something");
    }

    @Override
    public void afterCreateUserTask(CreateUserTaskAfterContext context) {
        log.info("afterCreateUserTask do something");
    }
}
```

```java
@Configuration
public class FlowableConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {

    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        springProcessEngineConfiguration.setCreateUserTaskInterceptor(new YxxmgCreateUserTaskInterceptor());
    }
}
```

## 自定义任务监听器

1. 监听任务创建、完成事件推送其他系统待办已办

   `org.flowable.common.engine.api.delegate.event.FlowableEngineEventType`参考这个里面的事件做监听

   ```java
   @Slf4j
   public class GlobalEventListener extends AbstractFlowableEventListener {
       @Override
       public void onEvent(FlowableEvent event) {
           if (event.getType().equals(FlowableEngineEventType.TASK_CREATED)) {
               //
               log.info("任务创建待办任务");
           }
           if (event.getType().equals(FlowableEngineEventType.TASK_COMPLETED)) {
               log.info("任务创建已办任务");
           }
           if (event.getType().equals(FlowableEngineEventType.PROCESS_COMPLETED)) {
               log.info("流程已完结");
           }
       }
   
       @Override
       public boolean isFailOnException() {
           return true;
       }
   
   }
   ```
   
   添加`flowable`配置文件中
   
   ```java
   @Configuration
   public class FlowableConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {
   
       @Override
       public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
           springProcessEngineConfiguration.setActivityFontName("宋体");
           springProcessEngineConfiguration.setLabelFontName("宋体");
           springProcessEngineConfiguration.setAnnotationFontName("宋体");
           springProcessEngineConfiguration.setIdGenerator(new YxxmgIdGenerator());
           springProcessEngineConfiguration.setEventListeners(Collections.singletonList(new GlobalEventListener()));
           springProcessEngineConfiguration.setCreateUserTaskInterceptor(new YxxmgCreateUserTaskInterceptor());
       }
   }
   ```
   
   
   
   程序运行结果
   
   ```
   2024-06-24 09:38:11.871  INFO 3940 --- [           main] c.y.f.handler.GlobalEventListener        : 任务创建已办任务
   2024-06-24 09:38:12.985  INFO 3940 --- [           main] c.y.f.i.YxxmgCreateUserTaskInterceptor   : beforeCreateUserTask do something
   2024-06-24 09:38:14.493  INFO 3940 --- [           main] c.y.f.i.YxxmgCreateUserTaskInterceptor   : afterCreateUserTask do something
   2024-06-24 09:38:14.493  INFO 3940 --- [           main] c.y.f.handler.GlobalEventListener        : 任务创建待办任务
   ```
   
## 自定义模型校验器

自定义校验器

```java
public class CustomBpmnValidator extends ValidatorImpl {
    @Override
    public void validate(BpmnModel bpmnModel, List<ValidationError> errorList) {
        Process mainProcess = bpmnModel.getMainProcess();
        List<UserTask> userTasks = mainProcess.findFlowElementsOfType(UserTask.class);
        List<ServiceTask> serviceTasks = mainProcess.findFlowElementsOfType(ServiceTask.class);
        List<StartEvent> startEvents = mainProcess.findFlowElementsOfType(StartEvent.class);
        List<EndEvent> endEvents = mainProcess.findFlowElementsOfType(EndEvent.class);
        // 只检查是否存在开始节点和节数节点
        if (CollectionUtils.isEmpty(startEvents) || CollectionUtils.isEmpty(endEvents)) {
            addError(errorList, "流程必须存在开始或结束节点", mainProcess, "流程必须存在开始或结束节点");
        }

    }
}
```

加入到`flowable`配置中

```java
@Configuration
public class CustomFlowableConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {

    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        springProcessEngineConfiguration.setActivityFontName("宋体");
        springProcessEngineConfiguration.setLabelFontName("宋体");
        springProcessEngineConfiguration.setAnnotationFontName("宋体");
        // 自定义BPMN模型校验器（deploy才会生效）
        springProcessEngineConfiguration.setProcessValidator(processValidator());
        // 自定义id生成器
        springProcessEngineConfiguration.setIdGenerator(new YxxmgIdGenerator());
        // 自定义事件监听器
        springProcessEngineConfiguration.setEventListeners(Collections.singletonList(new GlobalEventListener()));
        // 自定义创建用户任务拦截器
        springProcessEngineConfiguration.setCreateUserTaskInterceptor(new YxxmgCreateUserTaskInterceptor());
    }

    private ProcessValidator processValidator() {
        ProcessValidatorImpl processValidator = new ProcessValidatorImpl();
        ValidatorSet processValidatorSet = new ValidatorSetFactory().createFlowableExecutableProcessValidatorSet();
        processValidator.addValidatorSet(processValidatorSet);
        return processValidator;
    }
}
```

