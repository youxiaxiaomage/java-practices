package com.yxxmg.websocket.domain;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/12
 */
@Data
public class SocketMsg<T> {
    /**
     * 消息类型：1心跳 2登录 3业务操作
     */
    private Integer msgType;

    /**
     * 发送者用户ID
     */
    private String sendUserId;
    /**
     * 接受者用户ID
     */
    private String receivedUserId;

    /**
     * 业务类型
     */
    private Integer bizType;

    /**
     * 业务操作模块
     */
    private Integer bizOptModule;

    /**
     * 消息内容
     */
    private T msgBody;

}
