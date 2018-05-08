package com.duyi.edu.server.common.config;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Must {

    //前提配置
    String preConfig() default "";

    //前提配置的值
    String preValue() default "";

}
