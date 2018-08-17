package com.luoromeo.shiro.service;

import java.util.Set;

import com.luoromeo.commom.base.service.BaseService;
import com.luoromeo.shiro.entity.User;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月16日 14:05
 * @modified By
 */
public interface UserService extends BaseService<User> {

    /**
     * @description 创建账户
     * @author zhanghua.luo
     * @date 2018年08月16日 02:06:21
     * @param user
     * @return
     */
    User createUser(User user);

    /**
     * @description 修改密码
     * @author zhanghua.luo
     * @date 2018年08月16日 02:06:28
     * @param userId
     * @param newPassword
     * @return
     */
    void changePassword(Long userId, String newPassword);

    /**
     * @description 根据用户名查找用户
     * @author zhanghua.luo
     * @date 2018年08月16日 02:07:01
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * @description 根据用户名查找用户
     * @author zhanghua.luo
     * @date 2018年08月16日 02:07:01
     * @param username
     * @return
     */
    User findUser(String username, String password);

    Set<String> findPermissions(Long id);
}
