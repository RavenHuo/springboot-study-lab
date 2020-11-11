package com.raven.springcloud.dynamic.zuul.routing.config;

import com.raven.springcloud.dynamic.zuul.routing.vo.ZuulPathVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DynamicRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    private List<ZuulPathVO> zuulPathVOList;

    public DynamicRouteLocator(String servletPath, ZuulProperties properties, List<ZuulPathVO> zuulPathVOList) {
        super(servletPath, properties);
        this.zuulPathVOList = zuulPathVOList;
    }

    @Override
    public void refresh() {
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
        zuulPathVOList.stream().forEach(route -> {
            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
            BeanUtils.copyProperties(route, zuulRoute);
            routesMap.put(route.getPath(), zuulRoute);
        });
        return routesMap;
    }
}
