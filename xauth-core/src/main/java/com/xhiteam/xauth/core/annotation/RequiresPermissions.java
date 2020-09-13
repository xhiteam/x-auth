package com.xhiteam.xauth.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lambo.chen.2306@gmail.com
 * @date 2020/9/13 20:00
 *
 * 允许权限
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiresPermissions {

    /**
     * 权限值
     */
    String[] value();

    /**
     * 多个权限值时，处理逻辑
     */
    Logical logical() default Logical.OR;

}
