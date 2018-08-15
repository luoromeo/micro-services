package com.luoromeo.ccm.credit.controller;

import com.luoromeo.commom.base.controller.BaseController;
import com.luoromeo.commom.base.entity.ResultList;
import com.luoromeo.ccm.credit.entity.CreditAccount;
import com.luoromeo.ccm.credit.service.CreditAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年04月23日 23:37
 * @modified By
 */
@RestController
@RequestMapping("creditAccount")
public class CreditAccountController extends BaseController<CreditAccount, CreditAccountService> {

    @GetMapping("/money")
    public Mono<ResultList<CreditAccount>> findByAvailableCreditGtEq(BigDecimal money) {
        return Mono.just(this.getS().findByAvailableCreditGtEq(money));
    }
}
