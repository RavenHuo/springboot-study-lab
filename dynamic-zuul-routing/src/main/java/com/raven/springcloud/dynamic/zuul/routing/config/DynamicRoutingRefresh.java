package com.raven.springcloud.dynamic.zuul.routing.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @description:
 * @author: huorw
 * @create: 2020-11-12 21:20
 */
@Component
@Slf4j
public class DynamicRoutingRefresh {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private DynamicRouteLocator routeLocator;

    /**
     * RoutesRefreshedEvent 注册了事件
     * 每30秒刷新一次路由
     */
    @PostConstruct
    public void refreshRouting() {
        log.info("refreshRouting ------------------");
        RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(routeLocator);
        publisher.publishEvent(routesRefreshedEvent);
    }
}
