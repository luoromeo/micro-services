package com.luoromeo.ccm.credit.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年04月23日 16:02
 * @modified By
 */
@RestController
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
