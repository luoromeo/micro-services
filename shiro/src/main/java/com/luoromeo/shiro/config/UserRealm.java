package com.luoromeo.shiro.config;

import lombok.extern.slf4j.Slf4j;

import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.luoromeo.shiro.entity.User;
import com.luoromeo.shiro.service.UserService;
import com.luoromeo.shiro.utils.Constants;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月16日 10:25
 * @modified By
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userServiceImpl;


    @SuppressWarnings("unchecked")
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*
        3种情况下会进入该方法
        1、subject.hasRole(“admin”) 或 subject.isPermitted(“admin”)：自己去调用这个是否有什么角色或者是否有什么权限的时候；
        2、@RequiresRoles("admin") ：在方法上加注解的时候；
        3、[@shiro.hasPermission name = "admin"][/@shiro.hasPermission]：在页面上加shiro标签的时候，即进这个页面的时候扫描到有这个标签的时候
         */
        Session session = SecurityUtils.getSubject().getSession();
        //查询用户的权限
        User user = (User) session.getAttribute("Constants.SESSION_USER_INFO");
        Set<String> permissionList = userServiceImpl.findPermissions(user.getId());
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissionList);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String loginName = (String) token.getPrincipal();
        // 获取用户密码
        String password = new String((char[]) token.getCredentials());
        User user = userServiceImpl.findUser(loginName, password);

        if (user == null) {
            //没找到帐号
            throw new UnknownAccountException();
        }
        if(Boolean.TRUE.equals(!user.getAvailable())) {
            //帐号锁定
            throw new LockedAccountException();
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                //用户名
                user.getUsername(),
                //密码
                user.getPassword(),
                //salt=username+salt
                ByteSourceUtils.bytes(user.getCredentialsSalt()),
                //realm name
                getName()
        );

        //session中不需要保存密码
        user.setPassword("");
        //将用户信息放入session中
        SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_INFO, user);


        return authenticationInfo;
    }
}
