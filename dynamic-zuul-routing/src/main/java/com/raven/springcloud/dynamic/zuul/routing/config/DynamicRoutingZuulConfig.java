package com.raven.springcloud.dynamic.zuul.routing.config;

import com.raven.springcloud.dynamic.zuul.routing.entity.ZuulRoutingEntity;
import com.raven.springcloud.dynamic.zuul.routing.enums.TableStatusEnum;
import com.raven.springcloud.dynamic.zuul.routing.repository.ZuulRoutingRepository;
import com.raven.springcloud.dynamic.zuul.routing.vo.ZuulPathVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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
        List<ZuulRoutingEntity> zuulRoutingEntityList = zuulRoutingRepository.findByStatus(TableStatusEnum.NORMAL_STATUS.getCode());

        List<ZuulPathVO> zuulPathVOList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(zuulRoutingEntityList)) {
            zuulRoutingEntityList.stream().forEach(entity -> {
                ZuulPathVO zuulPathVO = new ZuulPathVO();
                BeanUtils.copyProperties(entity, zuulPathVO);
                zuulPathVOList.add(zuulPathVO);
            });
        }
        return new DynamicRouteLocator(this.server.getServlet().getContextPath(), this.zuulProperties, zuulPathVOList);
    }

}
