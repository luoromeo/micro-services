package com.luoromeo.ccm.credit.service.impl;

import com.luoromeo.commom.base.entity.Result;
import com.luoromeo.commom.base.entity.ResultList;
import com.luoromeo.commom.base.service.impl.BaseServiceImpl;
import com.luoromeo.ccm.credit.entity.CreditCardBill;
import com.luoromeo.ccm.credit.repository.CreditAccountRepository;
import com.luoromeo.ccm.credit.repository.CreditCardBillRepository;
import com.luoromeo.ccm.credit.service.CreditCardBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年04月23日 23:25
 * @modified By
 */
@Service
@Transactional
public class CreditCardBillServiceImpl extends BaseServiceImpl<CreditCardBill, CreditCardBillRepository> implements CreditCardBillService {

    @Autowired
    private CreditAccountRepository creditAccountRepository;

    @Override
    @CachePut(value = "creditCardBill", key = "#creditCardBill.id")
    public Result<CreditCardBill> save(CreditCardBill creditCardBill) {
        creditAccountRepository.findById(creditCardBill.getCreditAccount().getId()).ifPresent(creditCardBill::setCreditAccount);
        creditCardBill.setPaymentStatus(1);
        return super.save(creditCardBill);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ResultList<CreditCardBill> getByBillMonth(String billMonth) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
        try {
            return ResultList.success("获取账单列表成功", this.getDao().findAllByBillMonth(format.parse(billMonth)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ResultList.success("获取账单列表失败!");
    }

    @Override
    @SuppressWarnings("unchecked")
    public ResultList<CreditCardBill> getByPaymentStatus(Integer paymentStatus) {
        return ResultList.success("获取账单列表成功", this.getDao().findAllByPaymentStatus(paymentStatus));
    }

    @Override
    public void payment(Long id, BigDecimal paymentMoney) {
        this.getDao().findById(id).ifPresent(creditCardBill -> {
            if (creditCardBill.getNewBalance().subtract(paymentMoney).compareTo(BigDecimal.ZERO) == 0) {
                // 相等
                creditCardBill.setPaymentStatus(1);
                save(creditCardBill);
            }
        });
    }

    @Override
    public void restorePayment(Long id) {
        this.getDao().findById(id).ifPresent(creditCardBill -> {
            creditCardBill.setPaymentStatus(0);
            save(creditCardBill);
        });
    }

    @Override
    public Result<List<BigDecimal>> getBillTrends() {
        // 获取本年度所有账单的数据
        Calendar now = Calendar.getInstance();
        now.set(now.get(Calendar.YEAR), Calendar.JANUARY, 1, 0, 0, 0);
        Date start = now.getTime();
        now.set(now.get(Calendar.YEAR), Calendar.DECEMBER, 1, 0, 0, 0);
        Date end = now.getTime();
        List<CreditCardBill> creditCardBills = this.getDao().findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.between(root.get("billMonth").as(Date.class), start, end));
            Predicate[] p = new Predicate[list.size()];
            criteriaQuery.where(criteriaBuilder.and(list.toArray(p)));
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get("billMonth")));
            return criteriaQuery.getRestriction();
        });
        BigDecimal[] billTrends = new BigDecimal[12];
        Objects.requireNonNull(creditCardBills).stream().collect(Collectors.groupingBy(CreditCardBill::getBillMonth,
                Collectors.mapping(CreditCardBill::getNewBalance, Collectors.reducing(BigDecimal::add)))).forEach((k, v) -> {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(k);
                    billTrends[cal.get(Calendar.MONTH)] = v.orElse(BigDecimal.ZERO);
                });
        for (int i = 0; i < billTrends.length; i++) {
            if (billTrends[i] == null) {
                billTrends[i] = BigDecimal.ZERO;
            }
        }
        return Result.success("获取账单趋势成功!", Arrays.asList(billTrends));
    }
}
