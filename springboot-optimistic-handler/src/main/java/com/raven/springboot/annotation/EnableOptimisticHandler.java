package com.raven.springboot.annotation;

import com.raven.springboot.config.selector.OptimisticHandlerConfigurationSelector;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;

import java.lang.annotation.*;

/**
 * @author raven
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(OptimisticHandlerConfigurationSelector.class)
public @interface EnableOptimisticHandler {

    AdviceMode mode() default AdviceMode.PROXY;

    /**
     * 切面使用的注解  例如@SimpleLog
     * {@link OptimisticHandler}
     */
    Class<? extends Annotation> annotation() default OptimisticHandler.class;


    boolean proxyTargetClass() default false;

    /**
     * should be applied.
     * <p>The default is {@link Ordered#LOWEST_PRECEDENCE} in order to run
     * after all other post-processors, so that it can add an advisor to
     * existing proxies rather than double-proxy.
     */
    int order() default Ordered.LOWEST_PRECEDENCE;
}
