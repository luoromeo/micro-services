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
 * @description 还款记录表
 * @author zhanghua.luo
 * @date 2018年04月24日 00:07
 * @modified By
 */
@javax.persistence.Entity
@Getter
@Setter
public class PaymentRecord extends Entity {

    /**
     * 账单
     */
    @OneToOne(targetEntity = CreditCardBill.class, cascade = {CascadeType.DETACH})
    @JoinColumn(name = "credit_card_bill_id",referencedColumnName = "id")
    private CreditCardBill creditCardBill;

    /**
     * 还款金额
     */
    private BigDecimal paymentMoney;

    /**
     * 还款日
     */
    private Date paymentDate;

    /**
     * 还款方式
     */
    private Integer type;

    /**
     * 利息
     */
    private BigDecimal interest;
}
