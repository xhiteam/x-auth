package com.xhiteam.xauth.core.repository;

import com.xhiteam.xauth.core.model.Token;

import java.util.Calendar;
import java.util.Map;

/**
 * @author lambo.chen.2306@gmail.com
 * @date 2020/9/13 20:20
 * <p>
 * Token Repository
 **/
public interface TokenRepository {

    /**
     * 解析 Token Str
     *
     * @param tokenStr
     * @return {@link Token}
     */
    Token parseToken(String tokenStr);

    Token newToken();

    Token newToken(String id);

    Token newToken(String id, Calendar expire);

    Token newToken(String id, Calendar expire, Map<String, String> extensions);

    /**
     * 拷贝构造
     *
     * @param token
     * @return
     */
    Token newToken(Token token);

    /**
     * 刷新 token 过期时间
     * @param token
     * @return
     */
    Token refreshToken(Token token);
}
