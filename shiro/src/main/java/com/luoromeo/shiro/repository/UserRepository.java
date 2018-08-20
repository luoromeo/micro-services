package com.luoromeo.shiro.repository;

import com.luoromeo.commom.base.repository.BaseRepository;
import com.luoromeo.shiro.entity.User;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月16日 14:04
 * @modified By
 */
public interface UserRepository extends BaseRepository<User> {

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}