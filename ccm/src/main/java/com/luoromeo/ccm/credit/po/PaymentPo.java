package com.luoromeo.ccm.credit.po;

import com.luoromeo.ccm.credit.entity.BorrowRecord;
import com.luoromeo.ccm.credit.entity.PaymentRecord;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年05月13日 17:44
 * @modified By
 */
public class PaymentPo {

    private PaymentRecord paymentRecord;

    private BorrowRecord borrowRecord;

    public PaymentRecord getPaymentRecord() {
        return paymentRecord;
    }

    public void setPaymentRecord(PaymentRecord paymentRecord) {
        this.paymentRecord = paymentRecord;
    }

    public BorrowRecord getBorrowRecord() {
        return borrowRecord;
    }

    public void setBorrowRecord(BorrowRecord borrowRecord) {
        this.borrowRecord = borrowRecord;
    }
}
