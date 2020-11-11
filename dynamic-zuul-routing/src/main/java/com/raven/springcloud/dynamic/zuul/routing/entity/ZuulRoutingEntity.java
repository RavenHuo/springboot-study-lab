package com.raven.springcloud.dynamic.zuul.routing.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
/**
 * @description:
 * @author: huorw
 * @create: 2020-11-11 19:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_zuul_routing")
@Entity
public class ZuulRoutingEntity extends IdEntity{

    @Column(name = "path", columnDefinition = "varchar(512) comment '路径'")
    private String path;

    @Column(name = "url", columnDefinition = "varchar(512) comment 'url'")
    private String url;

    @Column(name = "serviceId", columnDefinition = "varchar(512) comment 'serviceId'")
    private String serviceId;
}
