package com.luoromeo.ccm.credit.service;

import com.luoromeo.commom.base.entity.Result;
import com.luoromeo.commom.base.entity.ResultList;
import com.luoromeo.commom.base.service.BaseService;
import com.luoromeo.ccm.credit.entity.PaymentRecord;
import com.luoromeo.ccm.credit.po.PaymentPo;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年04月23日 23:25
 * @modified By
 */
public interface PaymentRecordService extends BaseService<PaymentRecord> {

    ResultList<PaymentRecord> getByMonth(String month);

    /**
     * @description 还款
     * @author zhanghua.luo
     * @date 2018年05月13日 05:05:22
     * @param paymentPo
     * @return
     */
    Result<String> payment(PaymentPo paymentPo);
}
