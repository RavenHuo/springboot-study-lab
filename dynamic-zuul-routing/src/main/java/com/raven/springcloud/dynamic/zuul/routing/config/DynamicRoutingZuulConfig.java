package com.raven.springcloud.dynamic.zuul.routing.config;

import com.raven.springcloud.dynamic.zuul.routing.repository.ZuulRoutingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: huorw
 * @create: 2020-11-11 22:12
 */
@Configuration
public class DynamicRoutingZuulConfig {

    @Autowired
    private ZuulProperties zuulProperties;
    @Autowired
    private ServerProperties server;
    @Autowired
    private ZuulRoutingRepository zuulRoutingRepository;

    @Bean
    public DynamicRouteLocator routeLocator() {
        return new DynamicRouteLocator(this.server.getServlet().getContextPath(), this.zuulProperties, zuulRoutingRepository);
    }

}
