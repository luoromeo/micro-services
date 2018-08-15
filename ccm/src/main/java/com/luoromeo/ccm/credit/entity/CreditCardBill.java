package com.luoromeo.ccm.credit.entity;


import com.luoromeo.commom.base.entity.Entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description 信用账单
 * @author zhanghua.luo
 * @date 2018年04月23日 23:56
 * @modified By
 */
@javax.persistence.Entity
@Getter
@Setter
public class CreditCardBill extends Entity {

    /**
     * 账单周期开始时间
     */
    private Date statementCycleStart;

    /**
     * 结束时间
     */
    private Date statementCycleEnd;

    /**
     * 本期账单
     */
    private BigDecimal newBalance;

    /**
     * 最低还款额
     */
    private BigDecimal minPayment;

    /**
     * 到期还款日
     */
    private Date paymentDueDate;

    /**
     * 账单月份
     */
//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy年MM月")
    private Date billMonth;

    /**
     * 还款状态
     */
    private Integer paymentStatus;

    /**
     * 银行卡Id
     */
    @OneToOne(targetEntity = CreditAccount.class, cascade = {CascadeType.DETACH})
    @JoinColumn(name = "credit_card_id", referencedColumnName = "id")
    private CreditAccount creditAccount;
}
