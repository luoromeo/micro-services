package com.luoromeo.shiro.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.luoromeo.commom.base.repository.BaseRepository;
import com.luoromeo.shiro.entity.Permission;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月16日 15:58
 * @modified By
 */
public interface PermissionRepository extends BaseRepository<Permission> {

//    @Query(value = "select p.permissionCode from Permission p where p.id in (:ids)")
    List<Permission> findPermissionByIdIn(List<Long> ids);
}
