package com.xhiteam.xauth.core.exception;

import com.xhiteam.xauth.core.enums.TokenStatusCode;

/**
 * @author lambo.chen.2306@gmail.com
 * @date 2020/9/13 20:08
 *
 * 超时异常
 **/
public class ExpiredTokenException extends TokenException {

    private static final long serialVersionUID = 5481691538460663596L;

    public ExpiredTokenException() {
        super(TokenStatusCode.EXPIRED);
    }
}
