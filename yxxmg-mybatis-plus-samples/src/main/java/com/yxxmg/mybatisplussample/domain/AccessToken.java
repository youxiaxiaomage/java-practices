package com.yxxmg.mybatisplussample.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : access token
 * @since : 2022/11/7
 */
@Data
public class AccessToken implements Serializable {
    private static final long serialVersionUID = -4584364244499248002L;
    @JSONField(name = "refresh_token")
    private String refreshToken;
    @JSONField(name = "expires_in")
    private long expiresIn;
    @JSONField(name = "session_key")
    private String sessionKey;
    @JSONField(name = "access_token")
    private String accessToken;
    private String scope;
    @JSONField(name = "session_secret")
    private String sessionSecret;
}
