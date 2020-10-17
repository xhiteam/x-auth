package com.xhiteam.xauth.core.model;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lambo.chen.2306@gmail.com
 * @date 2020/9/13 21:39
 * <p>
 * {@link Token} 默认实现
 **/
public class TokenImpl implements Token {

    private String token;
    private Calendar expire;
    private int ttl;
    private String id;
    private List<String> roles;
    private List<String> permissions;
    private Map<String, String> extensions;

    public TokenImpl() {
        this.ttl = DEFAULT_EXPIRE_S;

        this.expire = Calendar.getInstance();
        this.expire.add(Calendar.SECOND, this.ttl);

        this.roles = new LinkedList<>();
        this.permissions = new LinkedList<>();
        this.extensions = new ConcurrentHashMap<>();
    }

    @Override
    public Token setTokenStr(String token) {
        this.token = token;
        return this;
    }

    @Override
    public String getTokenStr() {
        return token;
    }

    @Override
    public Calendar getExpire() {
        return expire;
    }

    @Override
    public Token setExpire(Calendar expire) {
        this.expire = expire;
        return this;
    }

    @Override
    public Token setTtl(int ttl) {
        this.ttl = ttl;
        return this;
    }

    @Override
    public int getTtl() {
        return ttl;
    }

    @Override
    public Token setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public List<String> getRoles() {
        return roles;
    }

    @Override
    public Token setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    @Override
    public List<String> getPermissions() {
        return permissions;
    }

    @Override
    public Token setPermissions(List<String> permissions) {
        this.permissions = permissions;
        return this;
    }

    @Override
    public Map<String, String> getExtensions() {
        return extensions;
    }

    @Override
    public Token setExtensions(Map<String, String> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public Token putExtension(String key, String value) {
        extensions.put(key, value);
        return this;
    }

    @Override
    public String getExtension(String key) {
        return extensions.get(key);
    }
}
