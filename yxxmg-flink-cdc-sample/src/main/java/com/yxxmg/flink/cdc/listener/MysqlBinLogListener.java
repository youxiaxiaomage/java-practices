package com.yxxmg.flink.cdc.listener;

import java.util.Properties;

import javax.annotation.Resource;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import com.ververica.cdc.connectors.mysql.table.StartupOptions;
import com.ververica.cdc.debezium.JsonDebeziumDeserializationSchema;
import com.yxxmg.flink.cdc.config.MysqlConfiguration;
import com.yxxmg.flink.cdc.sink.mysql.CustomMysqlSink;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/8/14
 */
@Component
public class MysqlBinLogListener implements CommandLineRunner {
    @Resource
    private CustomMysqlSink customSink;
    @Resource
    private MysqlConfiguration mysqlConfiguration;

    @Override
    public void run(String... args) throws Exception {
        Properties properties = new Properties();
        properties.setProperty("bigint.unsigned.handling.mode", "long");
        properties.setProperty("decimal.handling.mode", "double");

        MySqlSource<String> mySqlSource = MySqlSource.<String>builder().hostname(mysqlConfiguration.getHost())
            .port(mysqlConfiguration.getPort()).databaseList(mysqlConfiguration.getDatabases().toArray(new String[0]))
            .tableList(mysqlConfiguration.tables()).username(mysqlConfiguration.getUsername())
            .password(mysqlConfiguration.getPassword()).deserializer(new JsonDebeziumDeserializationSchema())
            .debeziumProperties(properties).startupOptions(StartupOptions.latest()).serverTimeZone("Asia/Shanghai")
            .build();

        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.enableCheckpointing(3000L);
        environment.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);
        DataStreamSource<String> dataStreamSource =
            environment.fromSource(mySqlSource, WatermarkStrategy.noWatermarks(), "mysql").setParallelism(1);
        dataStreamSource.addSink(customSink).setParallelism(1);
        environment.execute("Print Mysql Binlog");
    }
}
