package com.luoromeo.shiro.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

import com.luoromeo.commom.base.entity.Entity;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月16日 11:04
 * @modified By
 */
@javax.persistence.Entity
@Setter
@Getter
@Table(name = "sys_permission")
public class Permission extends Entity {

    private String menuCode;

    private String menuName;

    private String permissionCode;

    private String permissionName;

    private Integer requiredPermission;

    private Boolean available;
}
