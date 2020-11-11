package com.raven.springcloud.dynamic.zuul.routing.vo;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * @description:
 * @author: huorw
 * @create: 2020-11-11 21:59
 */
@Data
public class ZuulPathVO implements Serializable {

    private String path;

    private String url;

    private String serviceId;
}
