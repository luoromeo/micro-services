package com.luoromeo.commom.base.service.impl;

import com.luoromeo.commom.base.entity.Entity;
import com.luoromeo.commom.base.repository.BaseRepository;
import com.luoromeo.commom.base.service.BaseService;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年04月23日 23:21
 * @modified By
 */
public class BaseServiceImpl<T extends Entity, D extends BaseRepository> extends BaseServiceAdapter<T, D> implements BaseService<T> {
}
