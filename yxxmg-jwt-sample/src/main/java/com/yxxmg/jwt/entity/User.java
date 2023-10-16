package com.yxxmg.jwt.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/16
 */
@Data
@TableName("TBL_USER")
public class User implements Serializable, UserDetails {
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private String id;
    private String userName;
    private String password;
    @TableField(exist = false)
    private List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    /**
     * 这里使用email做为账号
     *
     * @return 用户账号
     */
    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * 账号是否未过期,下面的这个几个方法都是用来指定账号的状态的,因为该项目是一个Demo,所以这里全部返回true
     * 
     * @return true 未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号是否未锁定
     * 
     * @return true 未锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否未过期
     * 
     * @return true 未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账号是否激活
     * 
     * @return true 已激活
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
