package com.xhiteam.xauth.core.service;

import com.xhiteam.xauth.core.model.Token;

import java.lang.reflect.Method;

/**
 * @author lambo.chen.2306@gmail.com
 * @date 2020/9/13 20:50
 * <p>
 * 校验 service
 **/
public interface XAuthCheckService {

    /**
     * 校验所有 {@link #checkIgnore(Method)}、{@link #checkPermission(Method, Token)}、{@link #checkRole(Method, Token)}
     *
     * @return 校验结果
     */
    boolean check(Method method, Token token);

    /**
     * 校验是否为 {@link com.xhiteam.xauth.core.annotation.Ignore} 注释方法
     *
     * @param method 被校验的接口方法
     * @return 接口是否被忽略
     */
    boolean checkIgnore(Method method);

    /**
     * 校验是否有权限
     *
     * @param method 被校验的接口方法
     * @return 是否有访问接口的权限
     */
    boolean checkPermission(Method method, Token token);

    /**
     * 校验是否为允许通行的角色
     *
     * @param method 被娇艳的接口方法
     * @return 是否有访问接口的角色
     */
    boolean checkRole(Method method, Token token);
}
