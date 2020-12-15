package com.raven.springboot.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;

/**
 * @description:
 * @author: huorw
 * @create: 2020-12-12 11:21
 */
@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class ProxyOptimisticHandlerConfiguration extends AbstractOptimisticHandlerConfiguration{

    private final ApplicationContext applicationContext;

    public ProxyOptimisticHandlerConfiguration(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean(name = "ProxyOptimisticHandlerConfiguration")
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public OptimisticHandlerAnnotationBeanPostProcessor simpleLogAdvisor() {
        Assert.notNull(this.enableOptimisticHandler, "@EnableOptimisticHandler annotation metadata was not injected");
        OptimisticHandlerAnnotationBeanPostProcessor bpp = new OptimisticHandlerAnnotationBeanPostProcessor(applicationContext);
        Class<? extends Annotation> customAnnotation = this.enableOptimisticHandler.getClass("annotation");

        bpp.setAnnotationType(customAnnotation);
        bpp.setProxyTargetClass(this.enableOptimisticHandler.getBoolean("proxyTargetClass"));
        bpp.setOrder(this.enableOptimisticHandler.<Integer>getNumber("order"));
        return bpp;
    }

}