package com.raven.fun.aspect.config;

import com.raven.fun.aspect.annontation.EnableSimpleLog;
import com.raven.fun.aspect.annontation.SimpleLog;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.Nullable;

import java.lang.annotation.Annotation;

/**
 * @description:
 * @author: huorw
 * @create: 2020-12-08 23:34
 */
@Configuration
public abstract class AbstractSimpleLogConfiguration implements ImportAware {

    @Nullable
    protected AnnotationAttributes enableSimpleLog;

    /**
     * Set the annotation metadata of the importing @{@code Configuration} class.
     *
     * @param importMetadata
     */
    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        // 获取@EnableSimpleLog的参数
        this.enableSimpleLog = AnnotationAttributes.fromMap(
                importMetadata.getAnnotationAttributes(EnableSimpleLog.class.getName(), false));
        if (this.enableSimpleLog == null) {
            throw new IllegalArgumentException(
                    "@EnableAsync is not present on importing class " + importMetadata.getClassName());
        }
    }

    protected Class<? extends Annotation> defaultAnnotation() {
        return SimpleLog.class;
    }

    // 可拓展
//    /**
//     * Collect any {@link AsyncConfigurer} beans through autowiring.
//     */
//    @Autowired(required = false)
//    void setConfigurers(Collection<AsyncConfigurer> configurers) {
//        if (CollectionUtils.isEmpty(configurers)) {
//            return;
//        }
//        if (configurers.size() > 1) {
//            throw new IllegalStateException("Only one AsyncConfigurer may exist");
//        }
//        AsyncConfigurer configurer = configurers.iterator().next();
//        this.executor = configurer.getAsyncExecutor();
//        this.exceptionHandler = configurer.getAsyncUncaughtExceptionHandler();
//    }
}
