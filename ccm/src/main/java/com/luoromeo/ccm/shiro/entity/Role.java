package com.luoromeo.ccm.shiro.entity;

import com.luoromeo.commom.base.entity.Entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月15日 13:50
 * @modified By
 */
@Getter
@Setter
@javax.persistence.Entity
public class Role extends Entity implements Serializable {

    /**角色标识 程序中判断使用,如"admin"**/
    @Column
    private String role;
    /**角色描述,UI界面显示使用**/
    @Column
    private String description;
    /**是否可用,如果不可用将不会添加给用户**/
    @Column
    private Boolean available = Boolean.FALSE;

    public Role() {
    }

    public Role(String role, String description, Boolean available) {
        this.role = role;
        this.description = description;
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Role role = (Role) o;

        return id != null ? id.equals(role.id) : role.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                '}';
    }
}
