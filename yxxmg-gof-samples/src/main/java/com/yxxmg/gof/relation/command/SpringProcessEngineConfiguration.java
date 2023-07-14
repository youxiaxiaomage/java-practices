// package com.yxxmg.gof.relation.command;
//
// import org.flowable.engine.impl.cfg.ProcessEngineConfigurationImpl;
//
/// **
// * @author : yxxmg
// * @version : 1.0
// * @description :
// * @since : 2023/5/10
// */
// public class SpringProcessEngineConfiguration extends ProcessEngineConfigurationImpl {
// protected TaskService taskService = new TaskServiceImpl(this);
//
// @Override
// public void init() {
// initCommandExecutors();
// initServices();
// }
//
// private void initCommandExecutors() {
// initCommandInterceptors();
// initCommandExecutor();
// }
//
// @Override
// public void initServices() {
// initService(taskService);
// }
//
// private void initService(TaskService taskService) {
// if (taskService instanceof AbstractCommonEngineServiceImpl) {
// ((AbstractCommonEngineServiceImpl)taskService).setCommandExecutor(commandExecutor);
// }
// }
// }
