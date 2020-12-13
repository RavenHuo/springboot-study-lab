package com.raven.fun.aspect.annontation;


import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SimpleLog {

    // 信息
    String message() default "";
}
