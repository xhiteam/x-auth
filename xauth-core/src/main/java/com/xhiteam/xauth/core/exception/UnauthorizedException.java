package com.xhiteam.xauth.core.exception;

import com.xhiteam.xauth.core.enums.TokenStatusCode;

/**
 * @author lambo.chen.2306@gmail.com
 * @date 2020/9/13 20:58
 *
 * 无权限
 **/
public class UnauthorizedException extends TokenException {

    private static final long serialVersionUID = -1934607963769607704L;

    public UnauthorizedException() {
        super(TokenStatusCode.UNAUTHORIZED);
    }
}
