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

    /** 配置无状态sessionManager后的login方法 **/
    /*@RequestMapping("/")
    public void login(@RequestParam("code")String code, HttpServletRequest request){
        Map<String,Object> data = new HashMap<>();
        if(SecurityUtils.getSubject().isAuthenticated()){
　　　　　　　　//这里代码着已经登陆成功，所以自然不用再次认证，直接从rquest中取出就行了，
            data.put(StatelessSessionManager.HEADER_TOKEN_NAME,getServerToken());
            data.put(BIND,ShiroKit.getUser().getTel() != null);
            response(data);
        }
        LOG.info("授权码为:" + code);
        AuthorizationService authorizationService = authorizationFactory.getAuthorizationService(Constant.clientType);
        UserDetail authorization = authorizationService.authorization(code);



        Oauth2UserDetail userDetail = (Oauth2UserDetail) authorization;

        loginService.login(userDetail);
        User user = userService.saveUser(userDetail,Constant.clientType.toString());
        ShiroKit.getSession().setAttribute(ShiroKit.USER_DETAIL_KEY,userDetail);
        ShiroKit.getSession().setAttribute(ShiroKit.USER_KEY,user);
        data.put(BIND,user.getTel() != null);
　　　　　　//这里的代码，必须放到login之执行，因为login后，才会创建session，才会得到最新的token咯
        data.put(StatelessSessionManager.HEADER_TOKEN_NAME,getServerToken());
        response(data);
    }*/
}
