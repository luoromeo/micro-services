package com.luoromeo.shiro.repository;

import java.util.List;

import com.luoromeo.commom.base.repository.BaseRepository;
import com.luoromeo.shiro.entity.UserRoles;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月16日 16:00
 * @modified By
 */
public interface UserRolesRepository extends BaseRepository<UserRoles> {

    List<UserRoles> findAllByUserId(Long userId);
}
