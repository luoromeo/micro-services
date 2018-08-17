package com.luoromeo.shiro.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.luoromeo.commom.base.service.impl.BaseServiceAdapter;
import com.luoromeo.shiro.entity.Permission;
import com.luoromeo.shiro.repository.PermissionRepository;
import com.luoromeo.shiro.service.PermissionService;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月16日 15:59
 * @modified By
 */
@Service
public class PermissionServiceImpl extends BaseServiceAdapter<Permission, PermissionRepository> implements PermissionService {

    @Override
    public List<Permission> findPermissionsByIds(List<Long> ids) {
        return this.getDao().findPermissionByIdIn(ids);
    }
}
