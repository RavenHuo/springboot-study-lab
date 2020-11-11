package com.raven.springcloud.dynamic.zuul.routing.repository;

import com.raven.springcloud.dynamic.zuul.routing.entity.ZuulRoutingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: huorw
 * @create: 2020-11-11 22:14
 */
@Repository
public interface ZuulRoutingRepository extends JpaRepository<ZuulRoutingEntity, String> {

    List<ZuulRoutingEntity> findByStatus(Integer status);
}
