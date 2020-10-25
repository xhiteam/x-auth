package com.xhiteam.xauth.client;

import com.xhiteam.xauth.core.repository.TokenRepository;
import com.xhiteam.xauth.core.service.XAuthCheckService;
import com.xhiteam.xauth.impl.jwt.repository.JwtTokenRepository;
import com.xhiteam.xauth.impl.jwt.service.JwtXAuthCheckService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lambo.chen.2306@gmail.com
 * @date 2020/10/24 22:48
 * @since version 0.1.0
 **/
@Configuration
@EnableConfigurationProperties(XAuthProperties.class)
public class XAuthConfiguration {

    /**
     * 注入 JwtTokenRepository，采用 @Conditional 注解进行条件注入，
     *
     * @return
     */
    @Bean
    // 此版本只有 JWT implement，不需要进行条件注入
//    @Conditional(JwtXAuthCondition.class)
    public TokenRepository tokenRepository() {
        return new JwtTokenRepository();
    }

    @Bean
//    @Conditional(JwtXAuthCondition.class)
    public XAuthCheckService xAuthCheckService() {
        return new JwtXAuthCheckService();
    }

}
