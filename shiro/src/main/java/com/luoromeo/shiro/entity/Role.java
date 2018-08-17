package com.luoromeo.shiro.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

import com.luoromeo.commom.base.entity.Entity;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月16日 11:01
 * @modified By
 */
@Setter
@Getter
@javax.persistence.Entity
@Table(name = "sys_role")
public class Role extends Entity {

    private String roleName;

    private String description;

    private Boolean available;

}
