package com.raven.fun.aspect.config;

import com.raven.fun.aspect.config.advisor.ProxySimpleLogAnnotationAdvisor;
import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: huorw
 * @create: 2020-12-08 23:35
 */
public class SimpleLogAnnotationBeanPostProcessor extends AbstractBeanFactoryAwareAdvisingPostProcessor {

    @Nullable
    private Class<? extends Annotation> annotationType;

    public void setAnnotationType(Class<? extends Annotation> asyncAnnotationType) {
        Assert.notNull(asyncAnnotationType, "'asyncAnnotationType' must not be null");
        this.annotationType = asyncAnnotationType;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        super.setBeanFactory(beanFactory);
        Set<Class<? extends Annotation>> annotationTypes = new HashSet<>();

        if (this.annotationType != null) {
            annotationTypes.add(annotationType);
        }
        ProxySimpleLogAnnotationAdvisor advisor = new ProxySimpleLogAnnotationAdvisor(annotationTypes);
        advisor.setBeanFactory(beanFactory);
        this.advisor = advisor;
    }
}
