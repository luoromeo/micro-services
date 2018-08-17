package com.luoromeo.shiro.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

import com.luoromeo.commom.base.entity.Entity;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月16日 10:56
 * @modified By
 */
@javax.persistence.Entity
@Getter
@Setter
@Table(name = "sys_user")
public class User extends Entity {

    private String username;

    private String password;

    private String salt;

    private String nickname;

    private Boolean available;

    public String getCredentialsSalt() {
        return username + salt;
    }
}
