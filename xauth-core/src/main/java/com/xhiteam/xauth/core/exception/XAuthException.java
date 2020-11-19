package com.xhiteam.xauth.core.exception;

/**
 * @author lambo.chen.2306@gmail.com
 * @date 2020/9/13 20:06
 *
 * X-Auth 异常类
 **/
public class XAuthException extends RuntimeException {

    private static final long serialVersionUID = 65349475195020986L;

    public XAuthException(String message) {
        super(message);
    }
}
