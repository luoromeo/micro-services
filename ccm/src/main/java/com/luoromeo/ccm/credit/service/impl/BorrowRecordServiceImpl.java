package com.luoromeo.ccm.credit.service.impl;

import com.luoromeo.commom.base.entity.Result;
import com.luoromeo.commom.base.entity.ResultList;
import com.luoromeo.commom.base.service.impl.BaseServiceImpl;
import com.luoromeo.ccm.credit.entity.BorrowRecord;
import com.luoromeo.ccm.credit.entity.CreditAccount;
import com.luoromeo.ccm.credit.entity.CreditCardBill;
import com.luoromeo.ccm.credit.repository.BorrowChannelsRepository;
import com.luoromeo.ccm.credit.repository.BorrowRecordRepository;
import com.luoromeo.ccm.credit.service.BorrowRecordService;
import com.luoromeo.ccm.credit.service.CreditAccountService;
import com.luoromeo.ccm.credit.service.CreditCardBillService;
import com.luoromeo.tools.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParsePosition;
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
public class BorrowRecordServiceImpl extends BaseServiceImpl<BorrowRecord, BorrowRecordRepository> implements BorrowRecordService {

    @Autowired
    private CreditAccountService creditAccountServiceImpl;

    @Autowired
    private CreditCardBillService creditCardBillServiceImpl;

    @Autowired
    private BorrowChannelsRepository borrowChannelsRepository;

    @Override
    public Result<BorrowRecord> save(BorrowRecord borrowRecord) {
        CreditAccount creditAccount = (CreditAccount) creditAccountServiceImpl.findById(borrowRecord.getCreditAccount().getId()).getData();
        borrowChannelsRepository.findByName(borrowRecord.getBorrowChannels().getName()).ifPresent(borrowRecord::setBorrowChannels);
        if (creditAccount != null) {
            creditAccount.setAvailableCredit(creditAccount.getAvailableCredit().subtract(borrowRecord.getMoney()));
            borrowRecord.setCreditAccount(creditAccount);
            Long borrowDay = DateUtils.dateDiff(DateUtils.DAY, borrowRecord.getBorrowingDate(), borrowRecord.getPaymentDate());
            borrowRecord.setBorrowingDay(Math.toIntExact(borrowDay));
            borrowRecord.setFee(borrowRecord.getFee());
            BigDecimal interest = BigDecimal.ZERO;
            if (borrowRecord.getBorrowChannels().getName().contains("通刷")) {
                interest = borrowRecord.getMoney().multiply(borrowRecord.getBorrowChannels().getInterestRate().divide(BigDecimal.valueOf(100)))
                        .setScale(2, RoundingMode.HALF_UP);
            } else if (borrowRecord.getBorrowChannels().getName().contains("借呗")) {
                interest = borrowRecord.getMoney().multiply(borrowRecord.getBorrowChannels().getInterestRate().divide(BigDecimal.valueOf(100)))
                        .multiply(BigDecimal.valueOf(borrowDay)).setScale(2, RoundingMode.HALF_UP);
            }
            borrowRecord.setInterest(interest);
            // 如果是借呗 则再生成一条账单记录
            if (creditAccount.getName().contains("借呗")) {
                CreditCardBill creditCardBill = new CreditCardBill();
                creditCardBill.setCreditAccount(creditAccount);
                creditCardBill.setStatementCycleStart(borrowRecord.getBorrowingDate());
                creditCardBill.setStatementCycleEnd(borrowRecord.getPaymentDate());
                creditCardBill.setNewBalance(borrowRecord.getMoney());
                creditCardBill.setMinPayment(borrowRecord.getMoney());
                creditCardBill.setPaymentDueDate(new Date());
                creditCardBill.setBillMonth(new Date());
                creditCardBillServiceImpl.save(creditCardBill);
            }
        }

        return super.save(borrowRecord);
    }

    @Override
    public Result<BorrowRecord> delete(Long id) {
        this.getDao().findById(id).ifPresent(borrowRecord -> {
            // 恢复账户金额
            CreditAccount creditAccount = borrowRecord.getCreditAccount();
            creditAccount.setAvailableCredit(creditAccount.getAvailableCredit().add(borrowRecord.getMoney()));
            creditAccountServiceImpl.save(creditAccount);
            // todo 删除对应的账单数据

        });
        return super.delete(id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ResultList<BorrowRecord> getByMonth(String month) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");

        Date startMonth = sdf.parse(month, new ParsePosition(0));

        List<BorrowRecord> borrowRecordList = this.getDao().findAll((Specification<BorrowRecord>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.between(root.get("borrowingDate").as(Date.class), startMonth, getLastDayOfMonth(startMonth)));
            Predicate[] p = new Predicate[list.size()];
            criteriaQuery.where(criteriaBuilder.and(list.toArray(p)));
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("borrowingDate")));
            return criteriaQuery.getRestriction();
        });
        return ResultList.success("获取借款记录成功!", borrowRecordList);
    }

    @Override
    public ResultList<BigDecimal> getInterestTrends() {
        // 获取本年度所有借款的数据
        Calendar now = Calendar.getInstance();
        now.set(now.get(Calendar.YEAR), Calendar.JANUARY, 1, 0, 0, 0);
        Date start = now.getTime();
        now.set(now.get(Calendar.YEAR), Calendar.DECEMBER, 1, 0, 0, 0);
        Date end = now.getTime();
        List<BorrowRecord> borrowRecords = this.getDao().findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.between(root.get("belongYears").as(Date.class), start, end));
            Predicate[] p = new Predicate[list.size()];
            criteriaQuery.where(criteriaBuilder.and(list.toArray(p)));
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get("belongYears")));
            return criteriaQuery.getRestriction();
        });
        BigDecimal[] billTrends = new BigDecimal[12];
        Objects.requireNonNull(borrowRecords).stream().collect(Collectors.groupingBy(BorrowRecord::getBelongYears,
                Collectors.mapping(BorrowRecord::getInterest, Collectors.reducing(BigDecimal::add)))).forEach((k, v) -> {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(k);
                    billTrends[cal.get(Calendar.MONTH)] = v.orElse(BigDecimal.ZERO);
                });
        for (int i = 0; i < billTrends.length; i++) {
            if (billTrends[i] == null) {
                billTrends[i] = BigDecimal.ZERO;
            }
        }
        return ResultList.success("success", Arrays.asList(billTrends));
    }

    private Date getLastDayOfMonth(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        final int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, last);
        return cal.getTime();
    }

}
