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
import com.yxxmg.flink.cdc.sink.mysql.CustomMysqlSink;2024-08-14 14:36:08.957INFO 23980---[

Unnamed(1/1)#0]CustomMysqlSink  :收到变更原始数据:{"before":{"user_id":"1","user_name":"张三1","password":"123456"},"after":{"user_id":"1","user_name":"张三","password":"123456"},"source":{"version":"1.9.7.Final","connector":"mysql","name":"mysql_binlog_source","ts_ms":1723617368000,"snapshot":"false","db":"yxxmg","sequence":null,"table":"user","server_id":1,"gtid":null,"file":"mysql-bin.000004","pos":815,"row":0,"thread":0,"query":null},"op":"u","ts_ms":1723617368550,"transaction":null}
        2024-08-14 14:36:09.038INFO 23980---[

Unnamed(1/1)#0]CustomMysqlSink  :
更新 before:{"password":"123456","user_id":"1","user_name":"张三1"},after:{"password":"123456","user_id":"1","user_name":"张三"}
```

删除

```java
import com.yxxmg.flink.cdc.sink.mysql.CustomMysqlSink;2024-08-14 14:38:25.256INFO 23980---[

Unnamed(1/1)#0]CustomMysqlSink  :收到变更原始数据:{"before":{"user_id":"4","user_name":"赵六","password":"112233"},"after":null,"source":{"version":"1.9.7.Final","connector":"mysql","name":"mysql_binlog_source","ts_ms":1723617504000,"snapshot":"false","db":"yxxmg","sequence":null,"table":"user","server_id":1,"gtid":null,"file":"mysql-bin.000004","pos":1085,"row":0,"thread":0,"query":null},"op":"d","ts_ms":1723617504842,"transaction":null}
        2024-08-14 14:38:25.257INFO 23980---[

Unnamed(1/1)#0]CustomMysqlSink  :
删除 before:{"password":"112233","user_id":"4","user_name":"赵六"}
```

新增

```java
import com.yxxmg.flink.cdc.sink.mysql.CustomMysqlSink;
import com.yxxmg.flink.cdc.sink.mysql.CustomSink;2024-08-14 14:39:31.929INFO 23980---[

Unnamed(1/1)#0]CustomMysqlSink  :收到变更原始数据:{"before":null,"after":{"user_id":"4","user_name":"赵六","password":"123458"},"source":{"version":"1.9.7.Final","connector":"mysql","name":"mysql_binlog_source","ts_ms":1723617571000,"snapshot":"false","db":"yxxmg","sequence":null,"table":"user","server_id":1,"gtid":null,"file":"mysql-bin.000004","pos":1375,"row":0,"thread":0,"query":null},"op":"c","ts_ms":1723617571669,"transaction":null}
        2024-08-14 14:39:31.929INFO 23980---[

Unnamed(1/1)#0]CustomMysqlSink  :创建:{"password":"123458","user_id":"4","user_name":"赵六"}
```

> 具体可以参考这边可以解决集成问题
>
> https://github.com/apache/flink-cdc/wiki/FAQ%28ZH%29
>
>

### MySQL Binlog生成机制

1. **开启binlog**：MySQL需要设置`server_id`并在`my.cnf`配置文件中启用binlog，例如设置`log-bin=mysql-bin`选项启动二进制日志记录。
2. **事务提交与binlog写入**：每当数据库接收到一个事务请求，MySQL会在事务提交前记录下该事务的所有SQL语句（或者更底层的事件），这些事件按照发生顺序依次写入binlog文件中。
3. **binlog格式**：MySQL支持多种binlog格式，包括STATEMENT、ROW和MIXED，其中ROW格式对于CDC应用最为合适，因为它记录的是每一行数据的实际更改，而不是执行的SQL语句。
4. **binlog文件管理**：MySQL会按一定规则（比如文件大小或时间阈值）滚动binlog文件，创建新的binlog文件继续记录日志。老的binlog文件在满足清理策略时会被删除。

Flink CDC有时能读到binlog，有时不能的原因可能有以下几点：

1. **binlog清理**：如之前所述，如果MySQL服务器按照其配置的策略清理了较早的binlog文件，而Flink
   CDC恰好试图从已被清理的binlog文件中读取数据，那么就会发生无法读取的情况。
2. **binlog位置追踪失效**：Flink
   CDC依赖于Debezium或其他类似工具跟踪binlog的位置（GTID或FilePositon+Offset）。如果由于异常终止、重启等原因，未能正确保存和恢复上次读取的位置，可能会导致错过部分或全部binlog数据。
3. **并发访问与权限问题**：如果有多个进程或实例同时读取binlog，且没有妥善处理并发访问和同步，可能导致部分binlog事件未被读取。
4. **MySQL服务器状态变化**：例如，如果MySQL服务器在Flink CDC运行期间重启或发生主从切换，可能会影响到binlog的连续性。
5. **Flink CDC配置问题**：Flink CDC配置不准确或更新不当也可能导致无法正确读取binlog，例如连接参数错误、表过滤规则不正确等。

为了保证Flink CDC能够持续稳定地读取binlog，需要确保MySQL的binlog配置合理，且Flink
CDC的配置与MySQL服务器的binlog清理策略、安全策略等相协调。同时，要保持Flink CDC作业的健壮性，以便在异常情况发生时能够正确恢复binlog读取位置。
