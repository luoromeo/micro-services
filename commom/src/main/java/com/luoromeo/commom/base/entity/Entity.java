package com.luoromeo.commom.base.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年04月23日 22:22
 * @modified By
 */
@MappedSuperclass
@Getter
@Setter
public class Entity {

    @Id
    @Column(name="id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Version
    protected Integer version;

    @Column
    protected Date gmtCreate;

    @Column
    protected Date gmtModified;
}
