package com.xhiteam.xauth.core.repository;

import com.xhiteam.xauth.core.model.Token;
import com.xhiteam.xauth.core.model.TokenImpl;

import java.util.Calendar;
import java.util.Map;

/**
 * @author lambo.chen.2306@gmail.com
 * @date 2020/9/13 21:45
 * <p>
 * {@link TokenRepository} 基础实现
 **/
public abstract class AbstractTokenRepository implements TokenRepository {

    @Override
    public Token newToken() {
        return new TokenImpl();
    }

    @Override
    public Token newToken(String id) {
        return newToken().setId(id);
    }

    @Override
    public Token newToken(String id, Calendar expire) {
        return newToken(id).setExpire(expire);
    }

    @Override
    public Token newToken(String id, Calendar expire, Map<String, String> extensions) {
        return newToken(id, expire).setExtensions(extensions);
    }
}
