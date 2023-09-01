// package com.yxxmg.mybatis.flex.config;
//
// import org.apache.commons.lang3.StringUtils;
//
// import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
//
/// ***
// *
// * @author : zhaoyan
// * @version : 1.0
// * @description :
// * @since : 2023/8/31
// */
// public class P6SpyLogger implements MessageFormattingStrategy {
// @Override
// public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared,
// String sql, String url) {
// return StringUtils.isNotBlank(sql)
// ? " Consume Time：" + elapsed + " ms " + now + "\n Execute SQL：" + sql.replaceAll("[\\s]+", " ") + "\n" : "";
// }
// }
