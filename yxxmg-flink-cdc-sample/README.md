# flink cdc mysql binlog监测数据变化

### my.ini文件修改

```properties
[mysqld]
log-bin=mysql-bin
binlog_format=ROW
lower_case_table_names=1  
```



### 案例演示

修改

```java
2024-08-14 14:36:08.957  INFO 23980 --- [Unnamed (1/1)#0] com.yxxmg.flink.cdc.listener.CustomSink  : 收到变更原始数据:{"before":{"user_id":"1","user_name":"张三1","password":"123456"},"after":{"user_id":"1","user_name":"张三","password":"123456"},"source":{"version":"1.9.7.Final","connector":"mysql","name":"mysql_binlog_source","ts_ms":1723617368000,"snapshot":"false","db":"yxxmg","sequence":null,"table":"user","server_id":1,"gtid":null,"file":"mysql-bin.000004","pos":815,"row":0,"thread":0,"query":null},"op":"u","ts_ms":1723617368550,"transaction":null}
2024-08-14 14:36:09.038  INFO 23980 --- [Unnamed (1/1)#0] com.yxxmg.flink.cdc.listener.CustomSink  : 更新 before:{"password":"123456","user_id":"1","user_name":"张三1"}, after:{"password":"123456","user_id":"1","user_name":"张三"}
```

删除

```java
2024-08-14 14:38:25.256  INFO 23980 --- [Unnamed (1/1)#0] com.yxxmg.flink.cdc.listener.CustomSink  : 收到变更原始数据:{"before":{"user_id":"4","user_name":"赵六","password":"112233"},"after":null,"source":{"version":"1.9.7.Final","connector":"mysql","name":"mysql_binlog_source","ts_ms":1723617504000,"snapshot":"false","db":"yxxmg","sequence":null,"table":"user","server_id":1,"gtid":null,"file":"mysql-bin.000004","pos":1085,"row":0,"thread":0,"query":null},"op":"d","ts_ms":1723617504842,"transaction":null}
2024-08-14 14:38:25.257  INFO 23980 --- [Unnamed (1/1)#0] com.yxxmg.flink.cdc.listener.CustomSink  : 删除 before:{"password":"112233","user_id":"4","user_name":"赵六"}
```



新增

```java
2024-08-14 14:39:31.929  INFO 23980 --- [Unnamed (1/1)#0] com.yxxmg.flink.cdc.listener.CustomSink  : 收到变更原始数据:{"before":null,"after":{"user_id":"4","user_name":"赵六","password":"123458"},"source":{"version":"1.9.7.Final","connector":"mysql","name":"mysql_binlog_source","ts_ms":1723617571000,"snapshot":"false","db":"yxxmg","sequence":null,"table":"user","server_id":1,"gtid":null,"file":"mysql-bin.000004","pos":1375,"row":0,"thread":0,"query":null},"op":"c","ts_ms":1723617571669,"transaction":null}
2024-08-14 14:39:31.929  INFO 23980 --- [Unnamed (1/1)#0] com.yxxmg.flink.cdc.listener.CustomSink  : 创建:{"password":"123458","user_id":"4","user_name":"赵六"}
```

> 具体可以参考这边可以解决集成问题
>
> https://github.com/apache/flink-cdc/wiki/FAQ%28ZH%29

