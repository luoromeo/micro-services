package com.luoromeo.ccm.system.handler;

import com.google.common.collect.Maps;
import com.luoromeo.ccm.system.entity.Dict;
import com.luoromeo.ccm.system.repository.DictRepository;
import net.sf.cglib.beans.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.server.ServerResponse.status;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年05月17日 14:06
 * @modified By
 */
@Service
@Transactional
public class DictHandle {

    @Autowired
    private DictRepository dictRepository;

    public Mono<ServerResponse> getDict(ServerRequest req) {
        return dictRepository.findById(Long.valueOf(req.pathVariable("id"))).map(dict -> status(10000).syncBody(dict)).orElse(null);
    }

    public Mono<ServerResponse> getDictChildren(ServerRequest req) {
        return ok().syncBody(dictRepository.findAllByUpKey(Long.valueOf(req.pathVariable("upKey"))));
    }

    public Mono<ServerResponse> getDictAndChildren(ServerRequest req) {
        return dictRepository.findById(Long.valueOf(req.pathVariable("id"))).map(dict -> status(10000).syncBody(packData(dict, new HashMap<>())))
                .orElse(null);
    }

    private Map<Object, Object> packData(Dict dict, Map<Object, Object> result) {
        Map<String, Object> dictM = beanToMap(dict);
        List<Dict> children = getChildren(dict.getId());
        if (children == null || children.isEmpty()) {
            return result;
        } else {
            dictM.putIfAbsent("children", children);
            for (Dict d : children) {
                packData(d, result);
            }
        }
        return result;
    }

    private List<Dict> getChildren(Long upKey) {
        return dictRepository.findAllByUpKey(upKey);
    }

    private Map<String, Object> beanToMap(Object bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }
}
