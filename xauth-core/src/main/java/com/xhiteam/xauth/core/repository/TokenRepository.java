package com.xhiteam.xauth.core.repository;

import com.xhiteam.xauth.core.model.Token;

import java.util.Calendar;
import java.util.List;
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
     * @param tokenStr token字符串
     * @return {@link Token}
     */
    Token parseToken(String tokenStr);

    /**
     * 构建新的Token
     *
     * @return 新的Token
     */
    Token newToken();

    Token newToken(String id);

    Token newToken(String id, Calendar expire);

    Token newToken(String id, Calendar expire, Map<String, String> extensions);

    Token newToken(String id, List<String> roles, List<String> permissions, Map<String,String> extensions);

    Token newToken(String id, List<String> roles, List<String> permissions, Calendar expire, Map<String, String> extensions);


    /**
     * 拷贝构造
     *
     * @param token token
     * @return 拷贝完成的新token
     */
    Token newToken(Token token);

    /**
     * 刷新 token 过期时间
     * @param token token
     * @return 完成刷新的token
     */
    Token refreshToken(Token token);
}
