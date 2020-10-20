package com.xhiteam.xauth.core.repository;

import com.xhiteam.xauth.core.model.Token;
import com.xhiteam.xauth.core.model.TokenImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
        return newToken("");
    }

    @Override
    public Token newToken(String id) {
        return newToken(id, null, null, null);
    }

    @Override
    public Token newToken(String id, Calendar expire) {
        return newToken(id, null, null, expire, null);
    }

    @Override
    public Token newToken(String id, Calendar expire, Map<String, String> extensions) {
        return newToken(id, null, null, expire, extensions);
    }

    @Override
    public Token newToken(String id, List<String> roles, List<String> permissions, Map<String, String> extensions) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MILLISECOND, Token.DEFAULT_EXPIRE_S);
        return newToken(id, roles, permissions, cal, extensions);
    }
}
