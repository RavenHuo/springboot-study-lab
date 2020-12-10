package com.raven.fun.aspect.config;

import com.raven.fun.aspect.annontation.EnableSimpleLog;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;

/**
 * @description:
 * @author: huorw
 * @create: 2020-12-08 23:23
 */
@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class ProxySimpleLogConfiguration extends AbstractSimpleLogConfiguration {



    @Bean(name = "ProxySimpleLogConfiguration")
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public SimpleLogAnnotationBeanPostProcessor simpleLogAdvisor() {
        Assert.notNull(this.enableSimpleLog, "@EnableSimpleLog annotation metadata was not injected");
        SimpleLogAnnotationBeanPostProcessor bpp = new SimpleLogAnnotationBeanPostProcessor();
        Class<? extends Annotation> customAnnotation = this.enableSimpleLog.getClass("annotation");
        
        bpp.setAnnotationType(customAnnotation);
        bpp.setProxyTargetClass(this.enableSimpleLog.getBoolean("proxyTargetClass"));
        bpp.setOrder(this.enableSimpleLog.<Integer>getNumber("order"));
        return bpp;
    }


}
