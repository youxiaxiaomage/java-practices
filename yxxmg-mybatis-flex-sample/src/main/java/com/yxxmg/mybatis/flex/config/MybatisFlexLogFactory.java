// package com.yxxmg.mybatis.flex.config;
//
// import com.p6spy.engine.event.JdbcEventListener;
// import com.p6spy.engine.logging.P6LogOptions;
// import com.p6spy.engine.spy.P6Factory;
// import com.p6spy.engine.spy.P6LoadableOptions;
// import com.p6spy.engine.spy.option.P6OptionsRepository;
//
/// ***
// *
// * @author : zhaoyan
// * @version : 1.0
// * @description :
// * @since : 2023/8/31
// */
// public class MybatisFlexLogFactory implements P6Factory {
// @Override
// public P6LoadableOptions getOptions(P6OptionsRepository optionsRepository) {
// return new P6LogOptions(optionsRepository);
// }
//
// @Override
// public JdbcEventListener getJdbcEventListener() {
// return MybatisFlexLoggingEventListener.getInstance();
// }
// }
