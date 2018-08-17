package com.luoromeo.shiro.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

import com.luoromeo.commom.base.entity.Entity;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月16日 11:10
 * @modified By
 */
@javax.persistence.Entity
@Setter
@Getter
@Table(name = "sys_user_roles")
public class UserRoles extends Entity {

    private Long userId;

    private Long roleId;

    private Boolean available;
}
