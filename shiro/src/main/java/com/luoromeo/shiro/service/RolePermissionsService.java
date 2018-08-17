package com.luoromeo.shiro.service;

import java.util.List;

import com.luoromeo.commom.base.service.BaseService;
import com.luoromeo.shiro.entity.RolePermissions;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月16日 15:49
 * @modified By
 */
public interface RolePermissionsService extends BaseService<RolePermissions> {

    /**
     * 添加角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    void correlationPermissions(Long roleId, Long... permissionIds);

    /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    void uncorrelationPermissions(Long roleId, Long... permissionIds);

    List<RolePermissions> findRolePermissions(List<Long> roleIds);

}
