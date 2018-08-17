package com.luoromeo.ccm.credit.service.impl;

import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luoromeo.ccm.credit.entity.BorrowRecord;
import com.luoromeo.ccm.credit.entity.PaymentRecord;
import com.luoromeo.ccm.credit.po.PaymentPo;
import com.luoromeo.ccm.credit.repository.PaymentRecordRepository;
import com.luoromeo.ccm.credit.service.BorrowRecordService;
import com.luoromeo.ccm.credit.service.CreditCardBillService;
import com.luoromeo.ccm.credit.service.PaymentRecordService;
import com.luoromeo.commom.base.entity.ResultList;
import com.luoromeo.commom.base.entity.Results.Result;
import com.luoromeo.commom.base.service.impl.BaseServiceImpl;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年04月23日 23:25
 * @modified By
 */
@Service
@Transactional
public class PaymentRecordServiceImpl extends BaseServiceImpl<PaymentRecord, PaymentRecordRepository> implements PaymentRecordService {

    @Autowired
    private CreditCardBillService creditCardBillServiceImpl;

    @Autowired
    private BorrowRecordService borrowRecordServiceImpl;


    @Override
    public Result<PaymentRecord> save(PaymentRecord paymentRecord) {
        creditCardBillServiceImpl.payment(paymentRecord.getCreditCardBill().getId(), paymentRecord.getPaymentMoney());
        return super.save(paymentRecord);
    }

    @Override
    public Result<PaymentRecord> delete(Long id) {
        this.getDao().findById(id).ifPresent(paymentRecord -> creditCardBillServiceImpl.restorePayment(paymentRecord.getCreditCardBill().getId()));
        return super.delete(id);
    }

    @Override
    public ResultList<PaymentRecord> getByMonth(String month) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");

        Date startMonth = sdf.parse(month, new ParsePosition(0));

        List<PaymentRecord> paymentRecordList = this.getDao().findAll((Specification<PaymentRecord>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.between(root.get("paymentDate").as(Date.class), startMonth, getLastDayOfMonth(startMonth)));
            Predicate[] p = new Predicate[list.size()];
            criteriaQuery.where(criteriaBuilder.and(list.toArray(p)));
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("paymentDate")));
            return criteriaQuery.getRestriction();
        });
        return ResultList.success("获取还款记录成功!", paymentRecordList);
    }

    @Override
    public Result<String> payment(PaymentPo paymentPo) {
        PaymentRecord paymentRecord = paymentPo.getPaymentRecord();
        paymentRecord.setInterest(BigDecimal.ZERO);
        save(paymentRecord);
        if (paymentRecord.getType() == 1) {
            //套现还款
            BorrowRecord borrowRecord = paymentPo.getBorrowRecord();
            borrowRecordServiceImpl.save(borrowRecord);
        }
        return Result.success("还款成功!");
    }

    private Date getLastDayOfMonth(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        final int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, last);
        return cal.getTime();
    }
}
