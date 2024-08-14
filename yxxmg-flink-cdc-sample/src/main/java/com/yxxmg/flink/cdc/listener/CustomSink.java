package com.yxxmg.flink.cdc.listener;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yxxmg.flink.cdc.dto.BinLogDTO;
import com.yxxmg.flink.cdc.enums.OperationEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/8/14
 */
@Component
@Slf4j
public class CustomSink extends RichSinkFunction<String> {
    private static final long serialVersionUID = -6690947037392472301L;

    @Override
    public void invoke(String value, Context context) throws Exception {
        log.info("收到变更原始数据:{}", value);
        if (StringUtils.isNotBlank(value)) {
            BinLogDTO<?> binLogDTO = JSON.parseObject(value, new TypeReference<BinLogDTO<?>>() {});
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
}
