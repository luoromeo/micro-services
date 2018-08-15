package com.luoromeo.ccm.credit.entity;

import com.luoromeo.commom.base.entity.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @description 信用卡(账户)
 * @author zhanghua.luo
 * @date 2018年04月23日 22:32
 * @modified By
 */
@javax.persistence.Entity
@Getter
@Setter
public class CreditAccount extends Entity {

    /**
     * 名称
     */
    private String name;

    /**
     * 账户
     */
    private String account;

    /**
     * 额度
     */
    private BigDecimal creditLimit;

    /**
     * 可用额度
     */
    private BigDecimal availableCredit;

    /**
     * 还款日
     */
    private Integer dueDate;

}
