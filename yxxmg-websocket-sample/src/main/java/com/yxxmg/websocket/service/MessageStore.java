package com.yxxmg.websocket.service;

import javax.annotation.PostConstruct;
import javax.websocket.Session;

import org.springframework.stereotype.Service;

import com.yxxmg.websocket.server.WebSocketServer;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/12
 */
@Service
@Slf4j
public class MessageStore {
    public void deleteSession(Session session) {
        log.info("delete session:{}", session);
    }

    public void saveSession(Session session) {
        log.info("save session:{}", session);
    }

    @PostConstruct
    public void init() {
        WebSocketServer.setMessageStore(this);
    }

    public void updateConnectionHeartbeatTime(Session session, long l) {
        log.info("updateConnectionHeartbeatTime session {} l {}", session, l);
    }
}
