package com.raven.springboot.config;

import com.raven.springboot.annotation.EnableOptimisticHandler;
import com.raven.springboot.annotation.OptimisticHandler;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.Nullable;

import java.lang.annotation.Annotation;

/**
 * @description:
 * @author: huorw
 * @create: 2020-12-12 11:21
 */
@Configuration
public abstract class AbstractOptimisticHandlerConfiguration implements ImportAware {

    @Nullable
    protected AnnotationAttributes enableOptimisticHandler;

    /**
     * 获取Enablexx的配置属性
     *
     * @param importMetadata
     */
    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        // 获取@EnableSimpleLog的参数
        this.enableOptimisticHandler = AnnotationAttributes.fromMap(
                importMetadata.getAnnotationAttributes(EnableOptimisticHandler.class.getName(), false));
        if (this.enableOptimisticHandler == null) {
            throw new IllegalArgumentException(
                    "@EnableOptimisticHandler is not present on importing class " + importMetadata.getClassName());
        }
    }

    protected Class<? extends Annotation> defaultAnnotation() {
        return OptimisticHandler.class;
    }
}