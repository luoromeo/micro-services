package com.luoromeo.ccm.credit.repository;

import com.luoromeo.commom.base.repository.BaseRepository;
import com.luoromeo.ccm.credit.entity.CreditCardBill;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年04月23日 22:50
 * @modified By
 */
@CacheConfig(cacheNames = "creditCardBill")
public interface CreditCardBillRepository extends BaseRepository<CreditCardBill> {

    /**
     * @description 根据账单月份搜索
     * @author zhanghua.luo
     * @date 2018年05月03日 10:39:49
     * @param
     * @return
     */
    @Cacheable
    List<CreditCardBill> findAllByBillMonth(Date billMonth);

    /**
     * @description 根据信用账户搜索
     * @author zhanghua.luo
     * @date 2018年05月03日 10:47:28
     * @param creditCardId
     * @return
     */
    @Cacheable
    @Query(value = "select * from credit_card_bill t where t.credit_card_id = ?1", nativeQuery = true)
    List<CreditCardBill> findAllByCreditAccount(Long creditCardId);

    /**
     * @description
     * @author zhanghua.luo
     * @date 2018年05月11日 11:48:41
     * @param paymentStatus
     * @return
     */
    @Cacheable
    List<CreditCardBill> findAllByPaymentStatus(Integer paymentStatus);

    @Override
    @Cacheable
    List<CreditCardBill> findAll();
}
