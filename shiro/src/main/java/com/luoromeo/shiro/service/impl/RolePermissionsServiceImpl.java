package com.luoromeo.shiro.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.luoromeo.commom.base.service.impl.BaseServiceAdapter;
import com.luoromeo.shiro.entity.RolePermissions;
import com.luoromeo.shiro.repository.RolePermissionsRepository;
import com.luoromeo.shiro.service.RolePermissionsService;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月16日 15:49
 * @modified By
 */
@Service
public class RolePermissionsServiceImpl extends BaseServiceAdapter<RolePermissions, RolePermissionsRepository> implements RolePermissionsService {

    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        if (permissionIds != null && permissionIds.length > 0) {
            List<RolePermissions> rolePermissionsList = new LinkedList<>();
            for (Long permissionId : permissionIds) {
                RolePermissions rolePermissions = new RolePermissions();
                rolePermissions.setAvailable(true);
                rolePermissions.setRoleId(roleId);
                rolePermissions.setPermissionId(permissionId);
                rolePermissionsList.add(rolePermissions);
            }
            this.getDao().saveAll(rolePermissionsList);
        }

    }

    @Override
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        if (permissionIds != null && permissionIds.length > 0) {
            List<RolePermissions> rolePermissionsList = new LinkedList<>();
            for (Long permissionId : permissionIds) {
                RolePermissions rolePermissions = this.getDao().findByRoleIdAndPermissionId(roleId, permissionId);
                if (rolePermissions != null) {
                    rolePermissionsList.add(rolePermissions);
                }
            }
            this.getDao().deleteAll(rolePermissionsList);
        }
    }

    @Override
    public List<RolePermissions> findRolePermissions(List<Long> roleIds) {
        return this.getDao().findAllByRoleIdIn(roleIds);
    }
}
