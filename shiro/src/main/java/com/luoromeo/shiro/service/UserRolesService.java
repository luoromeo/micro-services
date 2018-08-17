package com.luoromeo.shiro.service;

import java.util.List;

import com.luoromeo.commom.base.service.BaseService;
import com.luoromeo.shiro.entity.UserRoles;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月16日 16:01
 * @modified By
 */
public interface UserRolesService extends BaseService<UserRoles> {

    /**
     * @description 添加用户-角色关系
     * @author zhanghua.luo
     * @date 2018年08月16日 02:06:45
     * @param userId
     * @param roleIds
     * @return
     */
    void correlationRoles(Long userId, Long... roleIds);

    /**
     * @description 移除用户-角色关系
     * @author zhanghua.luo
     * @date 2018年08月16日 02:06:55
     * @param userId
     * @param roleIds
     * @return
     */
    void uncorrelationRoles(Long userId, Long... roleIds);

    /**
     * @description 查找用户角色
     * @author zhanghua.luo
     * @date 2018年08月16日 04:13:52
     * @param userId
     * @return
     */
    List<UserRoles> findRoles(Long userId);
}
