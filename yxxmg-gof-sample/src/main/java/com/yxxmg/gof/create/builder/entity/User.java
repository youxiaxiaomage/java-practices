package com.yxxmg.gof.create.builder.entity;

import java.io.Serializable;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/20
 */
public class User implements Serializable {
    private static final long serialVersionUID = -3779241460049788168L;

    private String userId;
    private String userName;
    private Integer gender;

    public User(String userId, String userName, Integer gender) {
        this.userId = userId;
        this.userName = userName;
        this.gender = gender;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    @Override
    public String toString() {
        return "User{" + "userId='" + userId + '\'' + ", userName='" + userName + '\'' + ", gender=" + gender + '}';
    }

    public static class UserBuilder {
        private String userId;
        private String userName;
        private Integer gender;

        public UserBuilder() {}

        public String getUserId() {
            return userId;
        }

        public UserBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public String getUserName() {
            return userName;
        }

        public UserBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Integer getGender() {
            return gender;
        }

        public UserBuilder gender(Integer gender) {
            this.gender = gender;
            return this;
        }

        public User build() {
            return new User(userId, userName, gender);
        }
    }
}
