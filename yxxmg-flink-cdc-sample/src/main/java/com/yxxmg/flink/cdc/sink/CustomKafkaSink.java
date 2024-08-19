package com.yxxmg.flink.cdc.sink;

import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/8/15
 */
@Slf4j
public class CustomKafkaSink extends RichSinkFunction<String> {
    private static final long serialVersionUID = 8790922624991944185L;

    @Override
    public void invoke(String value, Context context) throws Exception {
        super.invoke(value, context);
    }
}
