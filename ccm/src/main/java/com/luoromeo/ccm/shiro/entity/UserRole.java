package com.luoromeo.ccm.shiro.entity;

import com.luoromeo.commom.base.entity.Entity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月15日 13:53
 * @modified By
 */
@Setter
@Getter
@javax.persistence.Entity
public class UserRole extends Entity implements Serializable {

    private Long userId;
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserRole userRole = (UserRole) o;

        if (roleId != null ? !roleId.equals(userRole.roleId) : userRole.roleId != null) {
            return false;
        }
        return userId != null ? userId.equals(userRole.userId) : userRole.userId == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
