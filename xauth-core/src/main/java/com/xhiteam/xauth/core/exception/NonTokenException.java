package com.xhiteam.xauth.core.exception;

import com.xhiteam.xauth.core.enums.TokenStatusCode;

/**
 * @author lambo.chen.2306@gmail.com
 * @date 2020/9/13 20:12
 *
 * token 不存在
 **/
public class NonTokenException extends TokenException {

    private static final long serialVersionUID = 596027793363269514L;

    public NonTokenException() {
        super(TokenStatusCode.NON_TOKEN);
    }
}
