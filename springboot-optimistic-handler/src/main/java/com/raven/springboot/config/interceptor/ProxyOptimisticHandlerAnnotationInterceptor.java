package com.raven.springboot.config.interceptor;

import com.raven.springboot.annotation.OptimisticHandler;
import com.raven.springboot.utils.ApplicationContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: huorw
 * @create: 2020-12-13 23:23
 */
@Slf4j
public class ProxyOptimisticHandlerAnnotationInterceptor extends AbstractOptimisticHandlerAnnotationInterceptor {

    private ApplicationContext applicationContext;

    public ProxyOptimisticHandlerAnnotationInterceptor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    private static final String METHOD_SPLIT_CHAR = "#";

    /**
     * 此处实现处理 乐观锁方法
     *
     * @param userDeclaredMethod
     */
    @Override
    protected void optimisticHandler(Method userDeclaredMethod) throws Throwable{
        OptimisticHandler annotation = userDeclaredMethod.getAnnotation(OptimisticHandler.class);
        if (null != annotation) {
            log.info("optimisticHandler--------- msg={}", annotation.exceptionMsg());

            String handlerMethod = annotation.handlerMethod();
            if (StringUtils.isNotBlank(handlerMethod)) {
                String[] strArray = handlerMethod.split(METHOD_SPLIT_CHAR);

                Assert.notNull(strArray, "parse optimisticHandler method error");

                Assert.isTrue(strArray.length == 2, "parse optimisticHandler method error");
                String beanName = strArray[0];
                String methodName = strArray[1];

                Object bean = applicationContext.getBean(beanName);

                Assert.notNull(strArray, "optimisticHandler bean is not defined");


                Method method = bean.getClass().getDeclaredMethod(methodName);

                Assert.notNull(strArray, "optimisticHandler method is not defined");

                method.invoke(bean);
            }


        }
    }
}
