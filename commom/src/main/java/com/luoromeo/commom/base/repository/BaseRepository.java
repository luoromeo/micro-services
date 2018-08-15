package com.luoromeo.commom.base.repository;

import com.luoromeo.commom.base.entity.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年04月23日 22:50
 * @modified By
 */
public interface BaseRepository<T extends Entity> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
}
