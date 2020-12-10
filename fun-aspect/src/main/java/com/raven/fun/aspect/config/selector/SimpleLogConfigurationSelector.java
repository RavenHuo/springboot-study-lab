package com.raven.fun.aspect.config.selector;

import com.raven.fun.aspect.annontation.EnableSimpleLog;
import com.raven.fun.aspect.config.ProxySimpleLogConfiguration;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;
import org.springframework.lang.Nullable;

/**
 * @description:
 * @author: huorw
 * @create: 2020-12-08 23:20
 */

public class SimpleLogConfigurationSelector extends AdviceModeImportSelector<EnableSimpleLog> {

    @Override
    @Nullable
    protected String[] selectImports(AdviceMode adviceMode) {
        switch (adviceMode) {
            case PROXY:
                return new String[]{ProxySimpleLogConfiguration.class.getName()};
            case ASPECTJ:
                return new String[]{};
            default:
                return new String[]{};
        }
    }
}
