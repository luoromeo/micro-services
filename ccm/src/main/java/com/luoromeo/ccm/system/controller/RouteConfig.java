package com.luoromeo.ccm.system.controller;

import com.luoromeo.ccm.system.handler.DictHandle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年05月17日 14:32
 * @modified By
 */
@Configuration
@EnableWebFlux
public class RouteConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(DictHandle dictHandle) {
        return route(GET("/dict/{id}"), dictHandle::getDictAndChildren).andRoute(GET("/dict/upKey/{upKey}"), dictHandle::getDictChildren);
    }
}
