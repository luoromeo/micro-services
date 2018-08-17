package com.luoromeo.shiro.service;

import com.luoromeo.commom.base.entity.Results;
import com.luoromeo.shiro.entity.User;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月16日 17:17
 * @modified By
 */
public interface LoginService {

    /**
     * 登录表单提交
     *
     * @param user
     * @return
     */
    Results.Result<String> authLogin(User user);

    /**
     * 退出登录
     *
     * @return
     */
    Results.Result logout(User user);
}
