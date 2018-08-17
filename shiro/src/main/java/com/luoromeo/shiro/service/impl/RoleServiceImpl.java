package com.luoromeo.shiro.service.impl;

import org.springframework.stereotype.Service;

import com.luoromeo.commom.base.service.impl.BaseServiceAdapter;
import com.luoromeo.shiro.entity.Role;
import com.luoromeo.shiro.repository.RoleRepository;
import com.luoromeo.shiro.service.RoleService;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月16日 15:42
 * @modified By
 */
@Service
public class RoleServiceImpl extends BaseServiceAdapter<Role, RoleRepository> implements RoleService {

    @Override
    public Role createRole(Role role) {
        return this.getDao().save(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        this.delete(roleId);
    }


}
