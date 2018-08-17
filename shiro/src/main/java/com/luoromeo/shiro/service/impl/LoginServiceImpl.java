package com.luoromeo.shiro.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.luoromeo.commom.base.entity.Results;
import com.luoromeo.shiro.entity.User;
import com.luoromeo.shiro.service.LoginService;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月16日 17:24
 * @modified By
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {


    @Override
    public Results.Result<String> authLogin(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            currentUser.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return Results.Result.failure("登录失败!");
        }
        return Results.Result.success("登录成功!");
    }

    @Override
    public Results.Result logout(User user) {
        return null;
    }
}
