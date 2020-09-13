package com.xhiteam.xauth.core.model;

import java.util.Calendar;
import java.util.Map;

/**
 * @author lambo.chen.2306@gmail.com
 * @date 2020/9/13 20:15
 * <p>
 * Token Model
 **/
public interface Token {

    /**
     * 默认超时时间 1800s
     */
    int DEFAULT_EXPIRE_S = 3600;

    Token setTokenStr(String token);

    String getTokenStr();

    Calendar getExpire();

    Token setExpire(Calendar expire);

    /**
     * 获取过期时间，默认 {@link #DEFAULT_EXPIRE_S}
     *
     * @param ttl
     * @return
     */
    Token setTtl(int ttl);

    int getTtl();

    /**
     * 唯一标识，userId，clientId 等
     *
     * @param id
     * @return
     */
    Token setId(String id);

    String getId();

    /**
     * 获取扩展数据 Map
     *
     * @return
     */
    Map<String, String> extensions();

    /**
     * 添加扩展数据
     *
     * @param key
     * @param value
     * @return
     */
    Token putExtension(String key, String value);

    /**
     * 根据 key 获取扩展数据
     *
     * @param key
     * @return
     */
    String getExtension(String key);
}
