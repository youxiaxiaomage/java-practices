package com.yxxmg.flink.cdc.job;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.runtime.state.hashmap.HashMapStateBackend;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yxxmg.flink.cdc.dto.BinLogDTO;
import com.yxxmg.flink.cdc.entity.User;
import com.yxxmg.flink.cdc.enums.OperationEnum;
import com.yxxmg.flink.cdc.sink.CustomKafka2MysqlSink;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : kafka数据同步到mysql中
 * @since : 2024/8/16
 */
@Component
@Slf4j
public class Kafka2MysqlJobHandler implements CommandLineRunner, Serializable {
    private static final long serialVersionUID = 5053866148151414952L;
    @Resource
    private CustomKafka2MysqlSink customKafka2MysqlSink;

    @Override
    public void run(String... args) throws Exception {
        // 1.创建执行环境
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.enableCheckpointing(3000L);
        environment.getCheckpointConfig()
            .setExternalizedCheckpointCleanup(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
        // checkpoint状态保存位置
        environment.setStateBackend(new HashMapStateBackend());
        // 重启2次，间隔2s，不配置的话，就是无限重启
        environment.setRestartStrategy(RestartStrategies.fixedDelayRestart(3, Time.seconds(2)));
        environment.setParallelism(1);
        Properties prop = new Properties();
        // 从当前开始读取数据
        prop.setProperty("auto.offset.reset", "latest");
        // 如果开启事务，需要将客户端的事务超时间小于broker的事务超时时间
        // broker默认值是为15分钟
        prop.setProperty("transaction.timeout.ms", "10");
        KafkaSource<String> kafkaSource = KafkaSource.<String>builder().setBootstrapServers("192.168.245.200:9092")
            .setGroupId("kafka-consumer").setTopics("yxxmg-flink").setStartingOffsets(OffsetsInitializer.latest())
            .setProperties(prop).setValueOnlyDeserializer(new SimpleStringSchema()).build();
        // 数据源
        DataStreamSource<String> dataStreamSource =
            environment.fromSource(kafkaSource, WatermarkStrategy.noWatermarks(), "kafka-source");
        // 打印error级别日志，醒目
        dataStreamSource.printToErr("-->").setParallelism(1);
        // 算子 从kafka->mysql
        dataStreamSource.addSink(
            JdbcSink.sink("insert into user(user_id, user_name, password) values(?,?,?)", (statement, value) -> {
                BinLogDTO<?> binLogDTO = JSON.parseObject(value, new TypeReference<BinLogDTO<?>>() {});
                if (Objects.equals(OperationEnum.CREATE, OperationEnum.of(binLogDTO.getOp()))) {
                    User user = JSON.parseObject(String.valueOf(binLogDTO.getAfter()), User.class);
                    Optional.ofNullable(user).ifPresent(u -> {
                        try {
                            statement.setString(1, u.getUserId());
                            statement.setString(2, u.getUserName());
                            statement.setString(3, u.getPassword());
                        } catch (SQLException e) {
                            log.error("statement set string value error", e);
                        }
                    });
                }
            }, new JdbcConnectionOptions.JdbcConnectionOptionsBuilder().withUrl(
                "jdbc:mysql://127.0.0.1:3306/yxxmg2?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&nullCatalogMeansCurrent=true")
                .withDriverName("com.mysql.cj.jdbc.Driver").withUsername("root").withPassword("123456").build()))
            .name("customKafkaSink").setParallelism(1);
        // 内部自定义sink
        // dataStreamSource.addSink(customKafka2MysqlSink).name("customKafkaSink").setParallelism(1);
        environment.execute("kafka2Mysql");

    }
}
