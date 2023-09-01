// package com.yxxmg.mybatis.flex.config;
//
// import java.sql.SQLException;
//
// import com.p6spy.engine.common.StatementInformation;
// import com.p6spy.engine.logging.LoggingEventListener;
//
/// ***
// *
// * @author : zhaoyan
// * @version : 1.0
// * @description :
// * @since : 2023/8/31
// */
// public class MybatisFlexLoggingEventListener extends LoggingEventListener {
// private static MybatisFlexLoggingEventListener INSTANCE;
//
// public MybatisFlexLoggingEventListener() {}
//
// public static MybatisFlexLoggingEventListener getInstance() {
// if (null == INSTANCE) {
// INSTANCE = new MybatisFlexLoggingEventListener();
// }
//
// return INSTANCE;
// }
//
// @Override
// public void onAfterExecuteBatch(StatementInformation statementInformation, long timeElapsedNanos,
// int[] updateCounts, SQLException e) {}
// }
