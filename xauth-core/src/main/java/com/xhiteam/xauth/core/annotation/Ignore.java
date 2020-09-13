package com.xhiteam.xauth.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lambo.chen.2306@gmail.com
 * @date 2020/9/13 19:59
 *
 * 忽略 X-Auth 校验，用于配置白名单
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Ignore {
}
