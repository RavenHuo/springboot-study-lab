package com.raven.springboot.config.interceptor;

import com.raven.springboot.annotation.OptimisticHandler;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: huorw
 * @create: 2020-12-13 23:23
 */
@Slf4j
public class ProxyOptimisticHandlerAnnotationInterceptor extends AbstractOptimisticHandlerAnnotationInterceptor {
    /**
     * 此处实现处理 乐观锁方法
     *
     * @param userDeclaredMethod
     */
    @Override
    protected void optimisticHandler(Method userDeclaredMethod) {
        OptimisticHandler annotation = userDeclaredMethod.getAnnotation(OptimisticHandler.class);
        if (null != annotation) {
            log.info("optimisticHandler--------- msg={}", annotation.exceptionMsg());
        }
    }
}
