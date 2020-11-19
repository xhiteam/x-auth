package com.xhiteam.xauth.core.exception;

import com.xhiteam.xauth.core.enums.TokenStatusCode;

/**
 * @author lambo.chen.2306@gmail.com
 * @date 2020/9/13 20:14
 *
 * Token 错误
 **/
public class ErrorTokenException extends TokenException {

    private static final long serialVersionUID = -546512641495830487L;

    public ErrorTokenException() {
        super(TokenStatusCode.ERROR);
    }
}
