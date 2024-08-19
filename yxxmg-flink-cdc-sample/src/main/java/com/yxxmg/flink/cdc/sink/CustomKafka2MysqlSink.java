package com.yxxmg.flink.cdc.sink;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yxxmg.flink.cdc.dto.BinLogDTO;
import com.yxxmg.flink.cdc.enums.OperationEnum;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/8/16
 */
@Component
public class CustomKafka2MysqlSink extends RichSinkFunction<String> {
    private static final Logger log = LoggerFactory.getLogger(CustomKafka2MysqlSink.class);

    @Override
    public void invoke(String value, Context context) throws Exception {
        log.info("value:{}", value);
        BinLogDTO<?> binLogDTO = JSON.parseObject(value, new TypeReference<BinLogDTO<?>>() {});
        log.info("binLogDTO:{}", binLogDTO);
        OperationEnum operation = OperationEnum.of(binLogDTO.getOp());
        if (Objects.equals(operation, OperationEnum.CREATE)) {
            log.info("创建:{}", binLogDTO.getAfter());
        } else if (Objects.equals(operation, OperationEnum.UPDATE)) {
            log.info("更新 before:{}, after:{}", binLogDTO.getBefore(), binLogDTO.getAfter());
        } else if (Objects.equals(operation, OperationEnum.DELETE)) {
            log.info("删除 before:{}", binLogDTO.getBefore());
        } else {
            log.info("未知操作类型");
        }
    }
}
