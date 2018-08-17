package com.luoromeo.shiro.service;

import java.util.List;

import com.luoromeo.commom.base.service.BaseService;
import com.luoromeo.shiro.entity.Permission;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月16日 15:59
 * @modified By
 */
public interface PermissionService extends BaseService<Permission> {

    List<Permission> findPermissionsByIds(List<Long> ids);

}
