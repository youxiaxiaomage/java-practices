package com.yxxmg.flink.cdc.deserializer;

import com.alibaba.fastjson.JSON;
import com.ververica.cdc.connectors.shaded.org.apache.kafka.connect.source.SourceRecord;
import com.ververica.cdc.debezium.DebeziumDeserializationSchema;
import com.yxxmg.flink.cdc.dto.BinLogDTO;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.util.Collector;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/8/19
 */
@SuppressWarnings("rawtypes")
public class CustomDeserializationSchema implements DebeziumDeserializationSchema<BinLogDTO> {
    @Override
    public void deserialize(SourceRecord sourceRecord, Collector<BinLogDTO> collector) throws Exception {
        Object value = sourceRecord.value();
        collector.collect(JSON.parseObject(String.valueOf(value), BinLogDTO.class));
    }

    @Override
    public TypeInformation<BinLogDTO> getProducedType() {
        return TypeInformation.of(BinLogDTO.class);
    }
}
