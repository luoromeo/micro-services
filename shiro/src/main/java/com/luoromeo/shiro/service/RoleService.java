package com.luoromeo.shiro.service;

import com.luoromeo.commom.base.service.BaseService;
import com.luoromeo.shiro.entity.Role;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月16日 15:41
 * @modified By
 */
public interface RoleService extends BaseService<Role> {

    Role createRole(Role role);

    void deleteRole(Long roleId);
}
