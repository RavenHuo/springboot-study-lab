package com.raven.fun.aspect.annontation;

import com.raven.fun.aspect.config.selector.SimpleLogConfigurationSelector;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncAnnotationBeanPostProcessor;

import java.lang.annotation.*;

/**
 * @description:
 * @author: huorw
 * @create: 2020-12-08 23:20
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SimpleLogConfigurationSelector.class)
public @interface EnableSimpleLog {

    AdviceMode mode() default AdviceMode.PROXY;

    /**
     * 切面使用的注解  例如@SimpleLog
     * @return
     */
    Class<? extends Annotation> annotation() default SimpleLog.class;

    /**
     * Indicate whether subclass-based (CGLIB) proxies are to be created as opposed
     * to standard Java interface-based proxies.
     * <p><strong>Applicable only if the {@link #mode} is set to {@link AdviceMode#PROXY}</strong>.
     * <p>The default is {@code false}.
     * <p>Note that setting this attribute to {@code true} will affect <em>all</em>
     * Spring-managed beans requiring proxying, not just those marked with {@code @Async}.
     * For example, other beans marked with Spring's {@code @Transactional} annotation
     * will be upgraded to subclass proxying at the same time. This approach has no
     * negative impact in practice unless one is explicitly expecting one type of proxy
     * vs. another &mdash; for example, in tests.
     */
    boolean proxyTargetClass() default false;

    /**
     * Indicate the order in which the {@link AsyncAnnotationBeanPostProcessor}
     * should be applied.
     * <p>The default is {@link Ordered#LOWEST_PRECEDENCE} in order to run
     * after all other post-processors, so that it can add an advisor to
     * existing proxies rather than double-proxy.
     */
    int order() default Ordered.LOWEST_PRECEDENCE;
}
