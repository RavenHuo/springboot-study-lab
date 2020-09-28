package com.raven.version.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * <p>Title: WebConfig</p>
 * <p>
 * Description:
 * </p>
 *
 * @author 616352001@qq.com
 * @date 2019/11/13
 **/
@Configuration
public class ApiVersionWebMvcConfiguration extends WebMvcConfigurationSupport {

    /**
     *
     * 重写响应处理方法
     * 改为使用自定义的映射处理器
     * @return
     */
    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping handlerMapping = new ApiVersionRequestMappingHandler();
        handlerMapping.setOrder(0);
        handlerMapping.setInterceptors(getInterceptors());
        return handlerMapping;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }
}
