package com.raven.springboot.config.advice;

import com.raven.springboot.config.interceptor.ProxyOptimisticHandlerAnnotationInterceptor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.ApplicationContext;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @description:
 * @author: huorw
 * @create: 2020-12-08 23:37
 */
public class ProxyOptimisticHandlerAnnotationAdvisor extends AbstractOptimisticHandlerAnnotationAdvisor {


    private ApplicationContext applicationContext;


    public ProxyOptimisticHandlerAnnotationAdvisor(Set<Class<? extends Annotation>> annotationType, ApplicationContext applicationContext) {
        super(annotationType);
        this.applicationContext = applicationContext;
        this.buildAdvice();
    }

    @Override
    protected void buildPointcut(Set<Class<? extends Annotation>> annotationType){
        this.pointcut = buildLogPointcut(annotationType);
    }

    @Override
    protected void buildAdvice(){
        this.advice = new ProxyOptimisticHandlerAnnotationInterceptor(this.applicationContext);
    }

    /**
     * 构建  切面
     * @param asyncAnnotationTypes
     * @return
     */
    protected Pointcut buildLogPointcut(Set<Class<? extends Annotation>> asyncAnnotationTypes) {
        ComposablePointcut result = null;
        for (Class<? extends Annotation> annotationType : asyncAnnotationTypes) {
            Pointcut cpc = new AnnotationMatchingPointcut(annotationType, true);
            Pointcut mpc = new AnnotationMatchingPointcut(null, annotationType, true);
            if (result == null) {
                result = new ComposablePointcut(cpc);
            }
            else {
                result.union(cpc);
            }
            result = result.union(mpc);
        }
        return (result != null ? result : Pointcut.TRUE);
    }
}
