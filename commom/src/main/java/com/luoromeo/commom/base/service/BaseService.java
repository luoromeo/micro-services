package com.luoromeo.commom.base.service;

import com.luoromeo.commom.base.entity.Entity;
import com.luoromeo.commom.base.entity.Results.Result;
import com.luoromeo.commom.base.entity.ResultList;
import com.luoromeo.commom.base.entity.ResultPage;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年04月23日 22:40
 * @modified By
 */
public interface BaseService<T extends Entity> {

    Result save(T t);

    Result delete(Long id);

    Result update(T t);

    Result<T> findById(Long id);

    ResultList findAll();

    ResultPage page(Integer pageNo, Integer pageSize, String searchInfo, String sortInfo);

}
