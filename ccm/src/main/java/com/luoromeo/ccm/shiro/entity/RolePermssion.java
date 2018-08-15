package com.luoromeo.ccm.shiro.entity;

import com.luoromeo.commom.base.entity.Entity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月15日 13:52
 * @modified By
 */
@Setter
@Getter
@javax.persistence.Entity
public class RolePermssion extends Entity implements Serializable {

    private Long roleId;
    private Long permissionId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RolePermssion that = (RolePermssion) o;

        if (permissionId != null ? !permissionId.equals(that.permissionId) : that.permissionId != null) {
            return false;
        }
        return roleId != null ? roleId.equals(that.roleId) : that.roleId == null;
    }

    @Override
    public int hashCode() {
        int result = roleId != null ? roleId.hashCode() : 0;
        result = 31 * result + (permissionId != null ? permissionId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RolePermssion{" +
                "roleId=" + roleId +
                ", permissionId=" + permissionId +
                '}';
    }
}
