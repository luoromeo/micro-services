package com.luoromeo.shiro.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

import com.luoromeo.commom.base.entity.Entity;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月16日 11:00
 * @modified By
 */
@javax.persistence.Entity
@Setter
@Getter
@Table(name = "sys_role_permissions")
public class RolePermissions extends Entity {

    private Long roleId;

    private Long permissionId;

    private Boolean available;
}
