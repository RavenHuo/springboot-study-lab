package com.raven.springcloud.dynamic.zuul.routing.config;

import com.raven.springcloud.dynamic.zuul.routing.entity.ZuulRoutingEntity;
import com.raven.springcloud.dynamic.zuul.routing.enums.TableStatusEnum;
import com.raven.springcloud.dynamic.zuul.routing.repository.ZuulRoutingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: huorw
 * @create: 2020-11-11 21:38
 */
@Slf4j
public class DynamicRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    private ZuulRoutingRepository zuulRoutingRepository;

    public DynamicRouteLocator(String servletPath, ZuulProperties properties, ZuulRoutingRepository zuulRoutingRepository) {
        super(servletPath, properties);
        this.zuulRoutingRepository = zuulRoutingRepository;
    }

    // 刷新路由时 会执行此方法
    @Override
    public void refresh() {
        log.info("DynamicRouteLocator  refresh   dynamic routing---------------------");
        doRefresh();
    }

    /**
     * Compute a map of path pattern to route. The default is just a static map from the
     * {@link ZuulProperties}, but subclasses can add dynamic calculations.
     * @return map of Zuul routes
     */
    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<>();
        List<ZuulRoutingEntity> list = zuulRoutingRepository.findByStatus(TableStatusEnum.NORMAL_STATUS.getCode());
        list.stream().forEach(route -> {
            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
            BeanUtils.copyProperties(route, zuulRoute);
            zuulRoute.setId(route.getRouteId());
            routesMap.put(route.getPath(), zuulRoute);
        });
        return routesMap;
    }
}
