package com.luoromeo.ccm.system.repository;

import com.luoromeo.commom.base.repository.BaseRepository;
import com.luoromeo.ccm.system.entity.Dict;

import java.util.List;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年05月08日 16:30
 * @modified By
 */
public interface DictRepository extends BaseRepository<Dict> {

    List<Dict> findAllByUpKey(Long upKey);
}
