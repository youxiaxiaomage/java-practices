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

## 自定义流程实例启动拦截器

```java
@Slf4j
public class YxxmgStartProcessInstanceInterceptor implements StartProcessInstanceInterceptor {
    @Override
    public void beforeStartProcessInstance(StartProcessInstanceBeforeContext instanceContext) {
        log.info("before start process instance pre operation");
    }

    @Override
    public void afterStartProcessInstance(StartProcessInstanceAfterContext instanceContext) {
        log.info("after start process instance after operation");
    }

    @Override
    public void beforeStartSubProcessInstance(StartSubProcessInstanceBeforeContext instanceContext) {
        log.info("before start sub process instance pre operation");
    }

    @Override
    public void afterStartSubProcessInstance(StartSubProcessInstanceAfterContext instanceContext) {
        log.info("after start sub process instance after operation");
    }
}
```

```java
@Override
public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
    // 身份信息拦截器
    springProcessEngineConfiguration.setStartProcessInstanceInterceptor(new YxxmgStartProcessInstanceInterceptor());

}
```

## 自定义身份信息拦截器

```java
public class YxxmgIdentityLinkInterceptor implements IdentityLinkInterceptor {
    @Override
    public void handleCompleteTask(TaskEntity task) {
        // complete

    }

    @Override
    public void handleAddIdentityLinkToTask(TaskEntity taskEntity, IdentityLinkEntity identityLinkEntity) {
        // add
    }

    @Override
    public void handleAddAssigneeIdentityLinkToTask(TaskEntity taskEntity, String assignee) {
        // assignee
    }

    @Override
    public void handleAddOwnerIdentityLinkToTask(TaskEntity taskEntity, String owner) {
        // owner
    }

    @Override
    public void handleCreateProcessInstance(ExecutionEntity processInstanceExecution) {
        // create
    }

    @Override
    public void handleCreateSubProcessInstance(ExecutionEntity subProcessInstanceExecution,
        ExecutionEntity superExecution) {
        // create sub process
    }
}
```

```java
    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        // 身份信息拦截器
        springProcessEngineConfiguration.setIdentityLinkInterceptor(new YxxmgIdentityLinkInterceptor());
    }
```

## 自定义流程实例状态回调

```java
@Slf4j
public class YxxmgRuntimeInstanceStateChangeCallback implements RuntimeInstanceStateChangeCallback {
    @Override
    public void stateChanged(CallbackData callbackData) {
        log.info("stateChanged old state {}, new state {}", callbackData.getOldState(), callbackData.getNewState());
    }
}
```

```java
@Configuration
public class CustomFlowableConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {

    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        springProcessEngineConfiguration
            .setProcessInstanceStateChangedCallbacks(createProcessInstanceStateChangedCallbacks());
    }

    private Map<String, List<RuntimeInstanceStateChangeCallback>> createProcessInstanceStateChangedCallbacks() {
        Map<String, List<RuntimeInstanceStateChangeCallback>> callbackMap = Maps.newHashMap();
        callbackMap.put("customCallBack", Collections.singletonList(new YxxmgRuntimeInstanceStateChangeCallback()));
        return callbackMap;
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

## 魔改flowable异步任务

1. 首先关闭异步任务

   ```yaml
   flowable:
     async-executor-activate: false
     async-history-executor-activate: false
   ```

2. 自定义异步任务处理

   a. 处理逻辑

   ```java
   @Service
   public class AsyncTaskJobImpl implements AsyncTaskJob {
       @Resource
       private ManagementService managementService;
   
       @Override
       public void execute() {
           // TODO 分布式锁
           List<Job> jobList = managementService.createJobQuery().list();
           if (CollectionUtils.isNotEmpty(jobList)) {
               for (Job job : jobList) {
                   managementService.executeJob(job.getId());
               }
           }
       }
   }
   
   ```

   b. 定义接口

   ```java
   @RestController("/job")
   @RequiredArgsConstructor
   public class AsyncTaskJobController {
       private final AsyncTaskJob asyncTaskJob;
   
       @GetMapping("/executeJobs")
       public void executeJobs() {
           this.asyncTaskJob.execute();
       }
   
   }
   ```

3. 配置xxl-job调度任务