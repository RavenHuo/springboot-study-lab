package com.raven.api.encryption.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: huorw
 * @create: 2020-10-12 18:54
 */
@Slf4j
public class FeignInterceptor implements RequestInterceptor {
    /**
     * 在这里处理feign参数
     *
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {

    }
}
