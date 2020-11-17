package com.xhiteam.xauth.core.model;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @author lambo.chen.2306@gmail.com
 * @date 2020/9/13 20:15
 * <p>
 * Token Model
 **/
public interface Token {

    /**
     * 默认超时时间 3600
     */
    int DEFAULT_EXPIRE_S = 3600;

    Token setTokenStr(String token);

    String getTokenStr();

    Calendar getExpire();

    Token setExpire(Calendar expire);

    /**
     * 过期时间，默认 {@link #DEFAULT_EXPIRE_S}
     *
     * @param ttl 过期时间
     * @return token
     */
    Token setTtl(int ttl);

    int getTtl();

    /**
     * 唯一标识，userId，clientId 等
     *
     * @param id 唯一标识
     * @return token
     */
    Token setId(String id);

    String getId();

    /**
     * 角色列表
     *
     * @return 用户角色列表
     */
    List<String> getRoles();

    Token setRoles(List<String> roles);

    /**
     * 权限列表
     *
     * @return 用户权限列表
     */
    List<String> getPermissions();

    Token setPermissions(List<String> permissions);

    /**
     * 获取扩展数据 Map
     *
     * @return 扩展数据列表
     */
    Map<String, String> getExtensions();

    Token setExtensions(Map<String, String> extensions);

    /**
     * 添加扩展数据
     *
     * @param key   扩展数据的key
     * @param value 扩展数据的value
     * @return token
     */
    Token putExtension(String key, String value);

    /**
     * 根据 key 获取扩展数据
     *
     * @param key 扩展信息的key
     * @return 扩展信息的value
     */
    String getExtension(String key);
}
