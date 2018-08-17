package com.luoromeo.shiro.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luoromeo.commom.base.service.impl.BaseServiceAdapter;
import com.luoromeo.shiro.entity.Permission;
import com.luoromeo.shiro.entity.RolePermissions;
import com.luoromeo.shiro.entity.User;
import com.luoromeo.shiro.entity.UserRoles;
import com.luoromeo.shiro.repository.UserRepository;
import com.luoromeo.shiro.service.PermissionService;
import com.luoromeo.shiro.service.RolePermissionsService;
import com.luoromeo.shiro.service.UserRolesService;
import com.luoromeo.shiro.service.UserService;
import com.luoromeo.shiro.utils.PasswordHelper;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月16日 15:23
 * @modified By
 */
@Service
public class UserServiceImpl extends BaseServiceAdapter<User, UserRepository> implements UserService {


    @Autowired
    private UserRolesService userRolesServiceImpl;

    @Autowired
    private RolePermissionsService rolePermissionsServiceImpl;

    @Autowired
    private PermissionService permissionServiceImpl;


    @Override
    public User createUser(User user) {
        return this.getDao().save(user);
    }

    @Override
    public void changePassword(Long userId, String newPassword) {
        this.getDao().findById(userId).ifPresent(user -> {
            user.setPassword(newPassword);
            PasswordHelper.getInstance().encryptPassword(user);
            this.update(user);
        });

    }

    @Override
    public User findByUsername(String username) {
        return this.getDao().findByUsername(username);
    }

    @Override
    public User findUser(String username, String password) {
        return this.getDao().findByUsernameAndPassword(username, password);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<String> findPermissions(Long id) {
        List<UserRoles> userRoles = userRolesServiceImpl.findRoles(id);
        List<Long> roleIdList = new ArrayList<>();
        for (UserRoles userRole : userRoles) {
            roleIdList.add(userRole.getRoleId());
        }
        List<RolePermissions> rolePermissionsList = rolePermissionsServiceImpl.findRolePermissions(roleIdList);
        Set<Long> permissionsIdSet = new HashSet<>();
        for (RolePermissions rolePermissions : rolePermissionsList) {
            permissionsIdSet.add(rolePermissions.getPermissionId());
        }

        List<Permission> permissionList = permissionServiceImpl.findPermissionsByIds((List<Long>) permissionsIdSet);
        Set<String> permissionCodes = new HashSet<>();
        for (Permission permission : permissionList) {
            permissionCodes.add(permission.getPermissionCode());
        }
        return permissionCodes;
    }
}
