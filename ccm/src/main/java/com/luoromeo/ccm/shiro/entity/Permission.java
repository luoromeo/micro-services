package com.luoromeo.ccm.shiro.entity;

import com.luoromeo.commom.base.entity.Entity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月15日 13:48
 * @modified By
 */
@Setter
@Getter
@javax.persistence.Entity
public class Permission extends Entity implements Serializable {

    /**权限标识 程序中判断使用,如"user:create" **/
    private String permission;
    /**权限描述,UI界面显示使用**/
    private String description;
    /**是否可用,如果不可用将不会添加给用户**/
    private Boolean available = Boolean.FALSE;

    public Permission() {
    }

    public Permission(String permission, String description, Boolean available) {
        this.permission = permission;
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

        Permission role = (Permission) o;

        return id != null ? id.equals(role.id) : role.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Role{"
                + "id=" + id
                + ", permission='" + permission + '\''
                + ", description='" + description + '\''
                + ", available=" + available
                + '}';
    }
}
