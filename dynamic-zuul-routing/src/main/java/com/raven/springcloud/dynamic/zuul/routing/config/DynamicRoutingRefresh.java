package com.raven.springcloud.dynamic.zuul.routing.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.util.List;

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

    @PostConstruct
    public void refreshRouting() {
        log.info("refreshRouting ------------------");
        RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(routeLocator);
        publisher.publishEvent(routesRefreshedEvent);
    }
}
