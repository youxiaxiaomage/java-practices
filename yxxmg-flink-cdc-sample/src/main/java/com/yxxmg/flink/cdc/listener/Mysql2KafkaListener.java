package com.yxxmg.flink.cdc.listener;

import javax.annotation.Resource;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.runtime.state.hashmap.HashMapStateBackend;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import com.ververica.cdc.connectors.mysql.table.StartupOptions;
import com.ververica.cdc.debezium.JsonDebeziumDeserializationSchema;
import com.yxxmg.flink.cdc.config.MysqlConfiguration;

import java.util.Properties;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 监听mysql binlog数据推送到kafka中
 * @since : 2024/8/15
 */
@Component
public class Mysql2KafkaListener implements CommandLineRunner {
    @Resource
    private MysqlConfiguration mysqlConfiguration;

    @Override
    public void run(String... args) throws Exception {
        // 1.创建执行环境
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.enableCheckpointing(3000L);
        environment.getCheckpointConfig().setExternalizedCheckpointCleanup(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
        // checkpoint状态保存位置
        environment.setStateBackend(new HashMapStateBackend());
        // 重启2次，间隔2s，不配置的话，就是无限重启
        environment.setRestartStrategy(RestartStrategies.fixedDelayRestart(3, Time.seconds(2)));
        environment.setParallelism(1);




        Properties properties = new Properties();
        properties.setProperty("autoReconnect","true");
        properties.setProperty("bigint.unsigned.handling.mode", "long");
        properties.setProperty("decimal.handling.mode", "double");
        MySqlSource<String> mySqlSource = MySqlSource.<String>builder()
                .hostname(mysqlConfiguration.getHost())
                .port(mysqlConfiguration.getPort())
                .databaseList(mysqlConfiguration.getDatabases().toArray(new String[0]))
                .tableList(mysqlConfiguration.tables())
                .username(mysqlConfiguration.getUsername())
                .password(mysqlConfiguration.getPassword())
                // 自定义反序列化器
                .deserializer(new JsonDebeziumDeserializationSchema())
                .jdbcProperties(properties)
                .startupOptions(StartupOptions.latest())
                .serverTimeZone("Asia/Shanghai")
                .build();


        DataStreamSource<String> mysqlDataStreamSource =
                environment.fromSource(mySqlSource, WatermarkStrategy.noWatermarks(), "mysql");
        mysqlDataStreamSource.printToErr("-->").setParallelism(1);

        Properties prop = new Properties();
        prop.setProperty("bootstrap.servers", "192.168.245.200:9092");
        prop.setProperty("group.id", "kafka");
        //从当前开始读取数据
        prop.setProperty("auto.offset.reset", "latest");
        //如果开启事务，需要将客户端的事务超时间小于broker的事务超时时间
        //broker默认值是为15分钟
        prop.setProperty("transaction.timeout.ms", "10");
        String topic  = "yxxmg-flink";

        mysqlDataStreamSource.addSink(
            new FlinkKafkaProducer<>(
                topic,
                new SimpleStringSchema(),
                prop
        )).name("mysqlDS kafka write").setParallelism(1);

        //6.执行任务
        environment.execute("flink kafka write");

    }
}
