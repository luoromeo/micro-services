package com.luoromeo.ccm.credit.controller;

import com.luoromeo.commom.base.controller.BaseController;
import com.luoromeo.commom.base.entity.ResultList;
import com.luoromeo.ccm.credit.entity.BorrowRecord;
import com.luoromeo.ccm.credit.service.BorrowRecordService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年04月23日 23:37
 * @modified By
 */
@RestController
@RequestMapping("borrowRecord")
public class BorrowRecordController extends BaseController<BorrowRecord, BorrowRecordService> {

    @RequestMapping(value = "/month/{month}", method = RequestMethod.GET)
    public Mono<ResultList<BorrowRecord>> getByBillMonth(@PathVariable("month") String month) {
        return Mono.just(this.getS().getByMonth(month));
    }

    @GetMapping(value = "interestTrends")
    public Mono<ResultList<BigDecimal>> getBillTrends() {
        return Mono.just(this.getS().getInterestTrends());
    }
}
