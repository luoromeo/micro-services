package com.luoromeo.commom.base.service.impl;

import com.luoromeo.commom.base.entity.Entity;
import com.luoromeo.commom.base.entity.Results.Result;
import com.luoromeo.commom.base.entity.ResultList;
import com.luoromeo.commom.base.entity.ResultPage;
import com.luoromeo.commom.base.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年04月23日 22:49
 * @modified By
 */
@Transactional
public class BaseServiceAdapter<T extends Entity, D extends BaseRepository> {

    @Autowired
    private D dao;

    public Result<T> save(T t) {
        this.dao.save(t);
        return Result.success("保存成功!");
    }

    @SuppressWarnings("unchecked")
    public Result<T> delete(Long id) {
        Optional<T> t = this.dao.findById(id);
        t.ifPresent(t1 -> this.dao.delete(t1));
        return Result.success("删除成功!");
    }

    public Result<T> update(T t) {
        this.dao.save(t);
        return Result.success("更新成功!");
    }

    @SuppressWarnings("unchecked")
    public Result<T> findById(Long id) {
        Optional<T> model = this.dao.findById(id);
        if (model.isPresent()) {
            return Result.success("获取成功!", model.get());
        } else {
            return Result.failure("不存在!");
        }
    }

    @SuppressWarnings("unchecked")
    public ResultList<T> findAll() {
        return ResultList.success("获取列表成功!", this.dao.findAll());
    }

    @SuppressWarnings("unchecked")
    public ResultPage page(Integer pageNo, Integer pageSize, String searchInfo, String sortInfo) {
        Sort sort = new Sort(Sort.Direction.DESC, "gmtCreate");
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<T> page = this.dao.findAll(pageable);
        return new ResultPage(page.getContent(), page.getTotalElements(), page.getTotalPages());
    }

    public D getDao() {
        return dao;
    }
}
