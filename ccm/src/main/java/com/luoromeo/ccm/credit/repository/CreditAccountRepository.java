package com.luoromeo.ccm.credit.repository;

import com.luoromeo.commom.base.repository.BaseRepository;
import com.luoromeo.ccm.credit.entity.CreditAccount;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年04月23日 22:50
 * @modified By
 */
public interface CreditAccountRepository extends BaseRepository<CreditAccount> {
    
    /**
     * @description 
     * @author zhanghua.luo
     * @date 2018年05月07日 10:43:42
     * @param money
     * @return 
     */
    List<CreditAccount> findAllByAvailableCreditGreaterThanEqual(BigDecimal money);
}
