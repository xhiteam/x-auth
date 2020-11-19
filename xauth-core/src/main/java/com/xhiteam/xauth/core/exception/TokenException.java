package com.xhiteam.xauth.core.exception;

import com.xhiteam.xauth.core.enums.TokenStatusCode;

/**
 * @author lambo.chen.2306@gmail.com
 * @date 2020/9/13 20:11
 **/
public class TokenException extends XAuthException {

    private static final long serialVersionUID = 3112034048541417485L;

    private String code;

    public TokenException(TokenStatusCode tokenStatusCode) {
        super(tokenStatusCode.getMsg());
        this.code = code;
    }

    public TokenException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
