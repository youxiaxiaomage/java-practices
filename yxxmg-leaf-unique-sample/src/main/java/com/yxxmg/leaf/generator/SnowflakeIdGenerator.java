package com.yxxmg.leaf.generator;

import java.util.Random;

import com.google.common.base.Preconditions;
import com.yxxmg.leaf.IpUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 消费未来时间
 * @since : 2023/9/7
 */
@Slf4j
public class SnowflakeIdGenerator implements IdGenerator {
    /**
     * 2010-11-04 09:42:54
     */
    private static final long TWEPOCH = 1288834974657L;
    private static final long WORKER_ID_BITS = 10L;
    /**
     * 最大能够分配的workerid =1023
     */
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
    private static final long SEQUENCE_BITS = 12L;
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);
    private final long workerId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;
    private static final Random RANDOM = new Random();

    @Override
    public synchronized long nextLongId() {
        long timestamp = timeGen();
        int maxGap = 10;
        if (timestamp < lastTimestamp) {
            long offset = lastTimestamp - timestamp;
            if (offset <= maxGap) {
                try {
                    Thread.sleep(offset << 1);
                } catch (Exception e) {
                    log.error("Thread is interrupted while synchronizing to lastTimeStamp.", e);
                    throw new IllegalStateException("Thread is interrupted while synchronizing to lastTimeStamp.");
                }
                timestamp = timeGen();
                if (timestamp < lastTimestamp) {
                    log.error("currentTime is less than lastTimestamp too much (> 10ms).");
                    throw new IllegalStateException(
                        "currentTime is less than lastTimestamp too much (> 10ms) after synchronized.");
                }
            } else {
                log.error("currentTime is less than lastTimestamp too much (> 10ms).");
                throw new IllegalStateException(
                    "currentTime is less than lastTimestamp too much (> 10ms) after synchronized.");
            }
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                // seq 为0的时候表示是下一毫秒时间开始对seq做随机
                sequence = RANDOM.nextInt(100);
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 如果是新的ms开始
            sequence = RANDOM.nextInt(100);
        }
        lastTimestamp = timestamp;
        return ((timestamp - TWEPOCH) << TIMESTAMP_LEFT_SHIFT) | (workerId << SEQUENCE_BITS) | sequence;
    }

    @Override
    public synchronized String nextStringId() {
        return String.valueOf(this.nextLongId());
    }

    /**
     * @param zkAddress zk地址
     * @param port snowflake监听端口
     */
    public SnowflakeIdGenerator(String zkAddress, int port, String zkPath) {
        final String ip = IpUtils.getLocalHostAddress().getHostAddress();
        Preconditions.checkArgument(timeGen() > TWEPOCH, "Snowflake not support twepoch gt currentTime");
        SnowflakeZookeeperHolder holder = new SnowflakeZookeeperHolder(ip, String.valueOf(port), zkAddress, zkPath);
        log.info("twepoch:{} ,ip:{} ,zkAddress:{} port:{}", TWEPOCH, ip, zkAddress, port);
        boolean initFlag = holder.init();
        Preconditions.checkArgument(initFlag, "Snowflake Id Gen is not init ok");
        workerId = holder.getWorkerId();
        log.info("start success use zk workerid-{}", workerId);
        Preconditions.checkArgument(workerId >= 0 && workerId <= MAX_WORKER_ID, "workerID must gte 0 and lte 1023");
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }
}
