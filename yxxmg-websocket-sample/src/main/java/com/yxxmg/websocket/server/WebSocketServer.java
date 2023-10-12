package com.yxxmg.websocket.server;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import com.alibaba.fastjson.JSON;
import com.yxxmg.websocket.domain.SocketConstants;
import com.yxxmg.websocket.domain.SocketMsg;
import com.yxxmg.websocket.service.MessageStore;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/12
 */
@ServerEndpoint("/yxxmg/websocket/{userId}")
@Component
@Slf4j
@NoArgsConstructor
public class WebSocketServer {
    private static MessageStore messageStore;

    public static void setMessageStore(MessageStore messageStore) {
        WebSocketServer.messageStore = messageStore;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        messageStore.saveSession(session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId) {
        messageStore.deleteSession(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        log.warn("=========== 收到来自窗口" + session.getId() + "的信息:" + message);
        handleTextMessage(session, new TextMessage(message));
    }

    @OnError
    public void onError(Session session, @PathParam("userId") String userId, Throwable error) {
        log.error("=========== 发生错误");
        error.printStackTrace();
    }

    public void handleTextMessage(Session session, TextMessage message) throws Exception {
        log.warn("=========== Received message: {}", message.getPayload());
        // 更新心跳和过期时间
        messageStore.updateConnectionHeartbeatTime(session, System.currentTimeMillis());
        if (StringUtils.isBlank(message.getPayload())) {
            return;
        }
        if ("ping".equals(message.getPayload())) {
            // messageSender.sendHeartBeatMsgToClient(session, "pong");
            return;
        }

        SocketMsg socketMsg = JSON.parseObject(message.getPayload(), SocketMsg.class);
        if (socketMsg.getMsgType() == SocketConstants.MsgType.HEART_BEAT) {
            // 心跳消息
            // sendMsgToClient(session, "pong");

        } else if (socketMsg.getMsgType() == SocketConstants.MsgType.LOGIN) {
            // session里要包含用户信息，用户信息要与session进行绑定，链接建立完，立马调用登录接口，将用户信息和对应session存储起来
            if (StringUtils.isBlank(socketMsg.getSendUserId())) {
                throw new Exception("用户ID不能为空");
            }
            if (socketMsg.getBizOptModule() == SocketConstants.BizOptModule.TRTC) {
                // SocketMsg<MsgBodyTrtc> msgBody =
                // JSON.parseObject(message.getPayload(), new TypeReference<SocketMsg<MsgBodyTrtc>>() {});
                // if (StringUtils.isBlank(msgBody.getRoomId())) {
                // throw new Exception("房间号不能为空");
                // }
                // messageStore.saveSessionUser(session, msgBody.getSendUserId());
            }

        } else if (socketMsg.getMsgType() == SocketConstants.MsgType.BIZ_OPERATE) {
            if (StringUtils.isBlank(socketMsg.getSendUserId())) {
                throw new Exception("用户ID不能为空");
            }
            if (socketMsg.getBizOptModule() == SocketConstants.BizOptModule.TRTC) {
                // handleTrtcMsg(message);
            }

        } else {
            log.error("消息类型错误");
        }

    }

}
