package com.luoromeo.shiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.luoromeo.commom.base.entity.Results;
import com.luoromeo.shiro.entity.User;
import com.luoromeo.shiro.service.LoginService;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月17日 10:17
 * @modified By
 */
@RestController()
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginServiceImpl;

    @PostMapping("auth")
    @ResponseBody
    public Results.Result<String> login(User user) {
        return loginServiceImpl.authLogin(user);
    }
}
