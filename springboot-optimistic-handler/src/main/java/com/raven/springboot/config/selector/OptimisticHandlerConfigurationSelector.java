package com.raven.springboot.config.selector;

import com.raven.springboot.annotation.EnableOptimisticHandler;
import com.raven.springboot.config.ProxyOptimisticHandlerConfiguration;
import com.raven.springboot.config.aspect.DefaultOptimisticHandlerAspect;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: huorw
 * @create: 2020-12-12 11:21
 */
public class OptimisticHandlerConfigurationSelector extends AdviceModeImportSelector<EnableOptimisticHandler> {
    /**
     * Determine which classes should be imported based on the given {@code AdviceMode}.
     * <p>Returning {@code null} from this method indicates that the {@code AdviceMode}
     * could not be handled or was unknown and that an {@code IllegalArgumentException}
     * should be thrown.
     *
     * @param adviceMode the value of the {@linkplain #getAdviceModeAttributeName()
     *                   advice mode attribute} for the annotation specified via generics.
     * @return array containing classes to import (empty array if none;
     * {@code null} if the given {@code AdviceMode} is unknown)
     */
    @Override
    @NotNull
    protected String[] selectImports(AdviceMode adviceMode) {
        switch (adviceMode) {
            // 切面
            case ASPECTJ:
                return new String[]{DefaultOptimisticHandlerAspect.class.getName()};
            // 代理
            case PROXY:
                return new String[]{ProxyOptimisticHandlerConfiguration.class.getName()};
            default:
                return null;
        }
    }
}
