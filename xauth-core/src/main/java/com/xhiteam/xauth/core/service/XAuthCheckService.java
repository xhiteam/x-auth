package com.xhiteam.xauth.core.service;

import java.lang.reflect.Method;

/**
 * @author lambo.chen.2306@gmail.com
 * @date 2020/9/13 20:50
 * <p>
 * 校验 service
 **/
public interface XAuthCheckService {

    /**
     * 校验所有 {@link #checkIgnore(Method)}、{@link #checkPermission(Method)}、{@link #checkRole(Method)}
     *
     * @return
     */
    boolean check(Method method);

    /**
     * 校验是否为 {@link com.xhiteam.xauth.core.annotation.Ignore} 注释方法
     *
     * @param method
     * @return
     */
    boolean checkIgnore(Method method);

    /**
     * 校验是否有权限
     *
     * @param method
     * @return
     */
    boolean checkPermission(Method method);

    /**
     * 校验是否为允许通行的角色
     *
     * @param method
     * @return
     */
    boolean checkRole(Method method);
}
