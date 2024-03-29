package com.yxxmg.websocket.domain;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/12
 */
public interface SocketConstants {
    /**
     * 消息类型
     */
    interface MsgType {
        /**
         * 1心跳
         */
        int HEART_BEAT = 1;
        /*
         * 2登录
         */
        int LOGIN = 2;
        /*
         * 业务类型
         */
        int BIZ_OPERATE = 100;
    }

    /**
     * 业务类型
     */
    interface BizType {
        int JZJ = 1;

        int HDJ = 2;
    }

    interface BizOptModule {
        /**
         * 自定义业务类型
         */
        int TRTC = 1;
    }

    /**
     * 模块操作类型
     */
    interface OperateType {
        /**
         * 麦克风操作
         */
        int MICRO_PHONE = 1;
    }

}
