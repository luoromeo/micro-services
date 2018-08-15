package com.luoromeo.ccm.credit.repository;

import com.luoromeo.commom.base.repository.BaseRepository;
import com.luoromeo.ccm.credit.entity.BorrowChannels;

import java.util.Optional;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年05月08日 16:30
 * @modified By
 */
public interface BorrowChannelsRepository extends BaseRepository<BorrowChannels> {

    Optional<BorrowChannels> findByName(String name);
}
