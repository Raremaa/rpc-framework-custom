package com.masaiqi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sq.ma
 * @date 2020/1/15 下午1:55
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcService {

    /**
     * 实现的接口
     */
    Class<?> value();

    /**
     * 版本
     */
    String version() default "";
}
