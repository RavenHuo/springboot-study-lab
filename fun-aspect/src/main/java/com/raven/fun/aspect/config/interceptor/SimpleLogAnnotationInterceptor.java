package com.raven.fun.aspect.config.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @description:
 * @author: huorw
 * @create: 2020-12-08 23:37
 */
@Slf4j
public class SimpleLogAnnotationInterceptor implements MethodInterceptor {

    /**
     * 代理方法
     * @param invocation
     * @return
     */
    @Override
    public Object invoke(MethodInvocation invocation) {
        log.info("simple log invoke   ------------");
        return null;
    }
}
