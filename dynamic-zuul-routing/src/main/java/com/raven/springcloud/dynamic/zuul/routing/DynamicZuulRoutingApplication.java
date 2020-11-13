package com.raven.springcloud.dynamic.zuul.routing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.discovery.DiscoveryClientRouteLocator;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableZuulProxy
@EnableDiscoveryClient
@EnableEurekaClient
public class DynamicZuulRoutingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicZuulRoutingApplication.class, args);
    }

}
