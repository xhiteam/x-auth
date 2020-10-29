package com.xhiteam.xauth.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lambo.chen.2306@gmail.com
 * @date 2020/10/24 21:59
 **/
@ConfigurationProperties(prefix = "xauth")
public class XAuthProperties {

    /**
     * 拦截路径，多个路径采用 "," (逗号) 隔开
     */
    private String[] path = new String[]{"/**"};

    /**
     * 排除路径，此路径不进行 XAuth 校验多个路径采用 "," (逗号) 隔开
     */
    private String[] excludePath = new String[]{};

    public String[] getPath() {
        return path;
    }

    public void setPath(String[] path) {
        this.path = path;
    }

    public String[] getExcludePath() {
        return excludePath;
    }

    public void setExcludePath(String[] excludePath) {
        this.excludePath = excludePath;
    }
}
