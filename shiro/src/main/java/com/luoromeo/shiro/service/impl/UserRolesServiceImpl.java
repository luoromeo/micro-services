package com.luoromeo.shiro.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.luoromeo.commom.base.service.impl.BaseServiceAdapter;
import com.luoromeo.shiro.entity.UserRoles;
import com.luoromeo.shiro.repository.UserRolesRepository;
import com.luoromeo.shiro.service.UserRolesService;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月16日 16:01
 * @modified By
 */
@Service
public class UserRolesServiceImpl extends BaseServiceAdapter<UserRoles, UserRolesRepository> implements UserRolesService {

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {

    }

    @Override
    public void uncorrelationRoles(Long userId, Long... roleIds) {

    }

    @Override
    public List<UserRoles> findRoles(Long userId) {
        return this.getDao().findAllByUserId(userId);
    }
}
