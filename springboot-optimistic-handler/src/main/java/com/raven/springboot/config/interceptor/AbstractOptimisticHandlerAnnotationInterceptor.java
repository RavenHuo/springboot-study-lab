package com.raven.springboot.config.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.util.ClassUtils;

import javax.persistence.OptimisticLockException;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: huorw
 * @create: 2020-12-08 23:37
 */
@Slf4j
public abstract class AbstractOptimisticHandlerAnnotationInterceptor implements MethodInterceptor {

    /**
     * 代理方法
     * @param invocation
     * @return
     */
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            log.info("optimisticHandler invoke   ------------ method={}", invocation.getMethod());
            invocation.proceed();
        }
        // 捕获乐观锁异常
        catch (OptimisticLockException ex) {
            // 获取代理类
            Class<?> targetClass = (invocation.getThis() != null ? AopUtils.getTargetClass(invocation.getThis()) : null);
            // 获取代理方法
            Method specificMethod = ClassUtils.getMostSpecificMethod(invocation.getMethod(), targetClass);
            // 获取代理方法的实现方法
            final Method userDeclaredMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);
            optimisticHandler(userDeclaredMethod);
        }
        return null;
    }

    /**
     * 此处实现处理 乐观锁方法
     * @param userDeclaredMethod
     */
    protected abstract void optimisticHandler(Method userDeclaredMethod);

}
