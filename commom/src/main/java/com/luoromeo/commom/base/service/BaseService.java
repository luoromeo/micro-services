package com.luoromeo.commom.base.service;

import com.luoromeo.commom.base.entity.Entity;
import com.luoromeo.commom.base.entity.Results;
import com.luoromeo.commom.base.entity.ResultList;
import com.luoromeo.commom.base.entity.ResultPage;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年04月23日 22:40
 * @modified By
 */
public interface BaseService<T extends Entity> {

    Results save(T t);

    Results delete(Long id);

    Results update(T t);

    Results<T> findById(Long id);

    ResultList findAll();

    ResultPage page(Integer pageNo, Integer pageSize, String searchInfo, String sortInfo);

}
