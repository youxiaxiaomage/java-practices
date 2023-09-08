package com.yxxmg.leaf.generator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryUntilElapsed;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/7
 */
@Slf4j
@Data
public class SnowflakeZookeeperHolder implements Serializable {
    private static final long serialVersionUID = -6757225350989613035L;
    /**
     * 连接超时时间 默认10s
     */
    private static final int CONNECT_TIMEOUT_MS = 10000;
    /**
     * 会话超时时间 默认6s
     */
    private static final int SESSION_TIMEOUT_MS = 6000;

    /**
     * zk节点信息
     */
    private String zkAddressNode;
    /**
     * 监听地址
     */
    private String listenAddress;
    /**
     * workerId
     */
    private int workerId;
    /**
     * ip
     */
    private String ip;
    /**
     * 端口号
     */
    private String port;
    /**
     * 缓存路径
     */
    private String cachePath;
    /**
     * 连接字符串
     */
    private String connectionString;
    /**
     * 最后更新时间
     */
    private long lastUpdateTime;
    /**
     * 持久化路径
     */
    private String pathForever;

    public SnowflakeZookeeperHolder(String ip, String port, String connectionString, String zkPath) {
        this.ip = ip;
        this.port = port;
        this.listenAddress = String.format("%s:%s", ip, port);
        this.connectionString = connectionString;
        this.pathForever = "/snowflake/" + zkPath + "/forever";
        this.cachePath =
            System.getProperty("java.io.tmpdir") + File.separator + zkPath + "/leafconfig/{port}/workerID.properties";
    }

    /**
     * 初始化zk相关信息
     * 
     * @return true:初始化成功；false：初始化失败
     */
    public boolean init() {
        try {
            CuratorFramework curatorFramework =
                createWithOptions(this.connectionString, new RetryUntilElapsed(1000, 4));
            curatorFramework.start();
            Stat stat = curatorFramework.checkExists().forPath(pathForever);
            if (Objects.isNull(stat)) {
                // 不存在根节点，机器第一次启动， 创建/snowflake/ip:port-000000000, 并上传数据
                zkAddressNode = createNode(curatorFramework);
                // worker id 默认是0
                updateLocalWorkId(workerId);
                // 定时上报本机时间给forever节点
                scheduleUploadData(curatorFramework, zkAddressNode);
                return true;
            } else {
                // ip:port -> 00001
                Map<String, Integer> nodeMap = Maps.newHashMap();
                Map<String, String> realNode = Maps.newHashMap();
                List<String> keys = curatorFramework.getChildren().forPath(pathForever);
                for (String key : keys) {
                    String[] nodeKey = key.split("-");
                    realNode.put(nodeKey[0], key);
                    nodeMap.put(nodeKey[0], Integer.parseInt(nodeKey[1]));
                }
                Integer workerID = nodeMap.get(listenAddress);
                if (Objects.nonNull(workerID)) {
                    // 有自己的节点， zkAddressNode = ip:port
                    zkAddressNode = pathForever + "/" + realNode.get(listenAddress);
                    // 启动worker时会使用
                    workerId = workerID;
                    if (!checkInitTimestamp(curatorFramework, zkAddressNode)) {
                        throw new IllegalStateException(
                            "init timestamp check error, forever node timestamp gt this node time");
                    }
                    // 准备创建临时节点
                    doService(curatorFramework);
                    updateLocalWorkId(workerId);
                    log.info(
                        "[Old Node] find forever node have this endpoint ip-{} port-{} workID-{} child node and start success",
                        ip, port, workerId);
                } else {
                    // 表示新启动的节点，创建持久节点，不用check时间
                    String newNode = createNode(curatorFramework);
                    zkAddressNode = newNode;
                    String[] nodeKey = newNode.split("-");
                    workerId = Integer.parseInt(nodeKey[1]);
                    doService(curatorFramework);
                    updateLocalWorkId(workerId);
                    log.info(
                        "[New Node] can not find node on forever node that endpoint ip-{} port-{} workId-{}  create own node on forever node and start success",
                        ip, port, workerId);
                }
            }
        } catch (Exception e) {
            log.error("Start Node error", e);
            try (FileInputStream in = new FileInputStream(cachePath.replace("{port}", String.valueOf(port)))) {
                Properties properties = new Properties();
                properties.load(in);
                workerId = Integer.parseInt(properties.getProperty("workerId"));
                log.warn("START FAILED ,use local node file properties workerID-{}", workerId);
            } catch (Exception e1) {
                log.error("Read file error ", e1);
                return false;
            }
        }
        return true;
    }

    private void doService(CuratorFramework curatorFramework) {
        scheduleUploadData(curatorFramework, zkAddressNode);
    }

    private boolean checkInitTimestamp(CuratorFramework curatorFramework, String zkAddressNode) throws Exception {
        byte[] bytes = curatorFramework.getData().forPath(zkAddressNode);
        Endpoint endpoint = deBuildData(new String(bytes));
        return !(endpoint.getTimestamp() > System.currentTimeMillis());
    }

    private Endpoint deBuildData(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Endpoint.class);
    }

    private void scheduleUploadData(CuratorFramework curatorFramework, String zkAddressNode) {
        // 每3s上报数据
        Executors.newSingleThreadScheduledExecutor(r -> {
            Thread thread = new Thread(r, "schedule-upload-time");
            thread.setDaemon(true);
            return thread;
        }).scheduleWithFixedDelay(() -> updateNewData(curatorFramework, zkAddressNode), 1L, 3L, TimeUnit.SECONDS);
    }

    private void updateNewData(CuratorFramework curatorFramework, String zkAddressNode) {
        try {
            if (System.currentTimeMillis() < lastUpdateTime) {
                return;
            }
            curatorFramework.setData().forPath(zkAddressNode, buildData().getBytes());
            lastUpdateTime = System.currentTimeMillis();
        } catch (Exception e) {
            log.error("Update init data error path is {}, ex with ", zkAddressNode, e);
        }
    }

    private void updateLocalWorkId(int workerId) {
        File leafConfFile = new File(cachePath.replace("{port}", port));
        boolean exists = leafConfFile.exists();
        log.info("file exists status is {}", exists);
        if (exists) {
            try {
                FileUtils.writeStringToFile(leafConfFile, "workId=" + workerId, StandardCharsets.UTF_8.name());
                log.info("update file cache workId is {}", workerId);
            } catch (IOException e) {
                log.error("update file cache error ", e);
            }
        } else {
            // 不存在文件,父目录页肯定不存在
            try {
                boolean mkdirs = leafConfFile.getParentFile().mkdirs();
                log.info("init local file cache create parent dis status is {}, worker id is {}", mkdirs, workerId);
                if (mkdirs) {
                    if (leafConfFile.createNewFile()) {
                        FileUtils.writeStringToFile(leafConfFile, "workId=" + workerId, StandardCharsets.UTF_8.name());
                        log.info("local file cache workId is {}", workerId);
                    }
                } else {
                    log.warn("create parent dir error===");
                }
            } catch (IOException e) {
                log.warn("create workId conf file error", e);
            }
        }
    }

    private String createNode(CuratorFramework curatorFramework) throws Exception {
        try {
            return curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                .forPath(pathForever + "/" + listenAddress + "-", buildData().getBytes());
        } catch (Exception e) {
            log.error("create node error msg {} ", e.getMessage());
            throw e;
        }
    }

    private String buildData() throws JsonProcessingException {
        Endpoint endpoint = new Endpoint(ip, port, System.currentTimeMillis());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(endpoint);
    }

    private CuratorFramework createWithOptions(String connectionString, RetryUntilElapsed retryUntilElapsed) {
        return CuratorFrameworkFactory.builder().connectString(connectionString).retryPolicy(retryUntilElapsed)
            .connectionTimeoutMs(CONNECT_TIMEOUT_MS).sessionTimeoutMs(SESSION_TIMEOUT_MS).build();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Endpoint implements Serializable {
        private static final long serialVersionUID = 7502858999161542606L;
        private String ip;
        private String port;
        private long timestamp;
    }
}
