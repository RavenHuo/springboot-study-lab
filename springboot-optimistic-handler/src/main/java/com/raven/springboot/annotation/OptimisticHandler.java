package com.raven.springboot.annotation;

import java.lang.annotation.*;


@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OptimisticHandler {

    String handlerMethod() default "";

    String exceptionMsg() default "乐观锁异常--------------";
}
