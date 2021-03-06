package com.raven.fun.aspect.config.advisor;

import com.raven.fun.aspect.config.interceptor.SimpleLogAnnotationInterceptor;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @description:
 * @author: huorw
 * @create: 2020-12-08 23:37
 */
public abstract class AbstractSimpleLogAnnotationAdvisor implements PointcutAdvisor, BeanFactoryAware {

    public AbstractSimpleLogAnnotationAdvisor(Set<Class<? extends Annotation>> annotationType) {
        this.buildPointcut(annotationType);
        this.buildAdvice();
    }

    protected Advice advice;

    protected Pointcut pointcut;



    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public Pointcut getPointcut(){
        return this.pointcut;
    }

    protected abstract void buildPointcut(Set<Class<? extends Annotation>> annotationType);

    protected abstract void buildAdvice();

    @Override
    public boolean isPerInstance() {
        return false;
    }

    /**
     * Set the {@code BeanFactory} to be used when looking up executors by qualifier.
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        if (this.advice instanceof BeanFactoryAware) {
            ((BeanFactoryAware) this.advice).setBeanFactory(beanFactory);
        }
    }

}
