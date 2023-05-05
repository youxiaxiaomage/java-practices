package com.yxxmg.gof.createPattern.builder;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 构造器模式
 * @since : 2023/5/5
 */
@Data
@AllArgsConstructor
public class Client {
    private String clientId;
    private String clientName;
    private long requestTimeout;
    private long socketTimeout;
    private long responseTimeout;

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private String clientId;
        private String clientName;
        private long requestTimeout;
        private long socketTimeout;
        private long responseTimeout;

        UserBuilder() {}

        public UserBuilder clientName(String clientName) {
            this.clientName = clientName;
            return this;
        }

        public UserBuilder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public UserBuilder requestTimeout(long requestTimeout) {
            this.requestTimeout = requestTimeout;
            return this;
        }

        public UserBuilder socketTimeout(long socketTimeout) {
            this.socketTimeout = socketTimeout;
            return this;
        }

        public UserBuilder responseTimeout(long responseTimeout) {
            this.requestTimeout = responseTimeout;
            return this;
        }

        public Client build() {
            return new Client(clientId, clientName, requestTimeout, socketTimeout, responseTimeout);
        }
    }
}
