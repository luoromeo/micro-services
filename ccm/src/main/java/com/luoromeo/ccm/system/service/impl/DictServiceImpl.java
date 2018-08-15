package com.luoromeo.ccm.system.service.impl;

import com.luoromeo.commom.base.service.impl.BaseServiceImpl;
import com.luoromeo.ccm.system.entity.Dict;
import com.luoromeo.ccm.system.repository.DictRepository;
import com.luoromeo.ccm.system.service.DictService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年05月08日 16:32
 * @modified By
 */
@Service
@Transactional
public class DictServiceImpl extends BaseServiceImpl<Dict, DictRepository> implements DictService {
}
