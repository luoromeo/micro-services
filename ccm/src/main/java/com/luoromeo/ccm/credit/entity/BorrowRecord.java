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
 * @description 借款记录表
 * @author zhanghua.luo
 * @date 2018年04月24日 00:11
 * @modified By
 */
@javax.persistence.Entity
@Getter
@Setter
public class BorrowRecord extends Entity {

    @OneToOne(targetEntity = CreditAccount.class, cascade = {CascadeType.DETACH})
    @JoinColumn(name = "credit_account_id", referencedColumnName = "id")
    private CreditAccount creditAccount;

    @OneToOne(targetEntity = BorrowChannels.class, cascade = {CascadeType.DETACH})
    @JoinColumn(name = "borrow_channels_id", referencedColumnName = "id")
    private BorrowChannels borrowChannels;

    /**
     * 借款金额
     */
    private BigDecimal money;

    /**
     * 借款日期
     */
//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy年MM月dd日")
    private Date borrowingDate;

    /**
     * 还款日期
     */
//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy年MM月dd日")
    private Date paymentDate;

    /**
     * 借款天数
     */
    private Integer borrowingDay;

    /**
     * 利息
     */
    private BigDecimal interest;

    /**
     * 手续费
     */
    private BigDecimal fee;

    /**
     * 所属年月
     */
    private Date belongYears;
}
