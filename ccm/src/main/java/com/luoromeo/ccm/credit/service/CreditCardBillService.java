package com.luoromeo.ccm.credit.service;

import com.luoromeo.commom.base.entity.Result;
import com.luoromeo.commom.base.entity.ResultList;
import com.luoromeo.commom.base.service.BaseService;
import com.luoromeo.ccm.credit.entity.CreditCardBill;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年04月23日 23:25
 * @modified By
 */
public interface CreditCardBillService extends BaseService<CreditCardBill> {

    /**
     * @description 根据账单月份查找数据
     * @author zhanghua.luo
     * @date 2018年05月03日 10:50:59
     * @param billMonth
     * @return
     */
    ResultList<CreditCardBill> getByBillMonth(String billMonth);

    /**
     * @description 根据还款状态
     * @author zhanghua.luo
     * @date 2018年05月11日 11:46:10
     * @param paymentStatus
     * @return
     */
    ResultList<CreditCardBill> getByPaymentStatus(Integer paymentStatus);
    
    /**
     * @description
     * @author zhanghua.luo
     * @date 2018年05月11日 05:41:32
     * @param id
     * @param paymentMoney
     * @return 
     */
    void payment(Long id, BigDecimal paymentMoney);

    /**
     * @description 
     * @author zhanghua.luo
     * @date 2018年05月13日 03:59:47
     * @param id
     * @return 
     */
    void restorePayment(Long id);

    /**
     * @description 获取账单趋势
     * @author zhanghua.luo
     * @date 2018年05月14日 04:04:17
     * @param null
     * @return
     */
    Result<List<BigDecimal>> getBillTrends();

}
