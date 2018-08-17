package com.luoromeo.shiro.repository;

import java.util.List;

import com.luoromeo.commom.base.repository.BaseRepository;
import com.luoromeo.shiro.entity.RolePermissions;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月16日 15:48
 * @modified By
 */
public interface RolePermissionsRepository extends BaseRepository<RolePermissions> {

    RolePermissions findByRoleIdAndPermissionId(Long roleId, Long permissionId);

    List<RolePermissions> findAllByRoleIdIn(List<Long> roleIds);
}
