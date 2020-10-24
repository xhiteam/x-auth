package com.xhiteam.xauth.client;

import com.xhiteam.xauth.core.interceptor.XAuthInterceptor;
import com.xhiteam.xauth.core.repository.TokenRepository;
import com.xhiteam.xauth.core.service.XAuthCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lambo.chen.2306@gmail.com
 * @date 2020/10/24 22:12
 **/
@EnableConfigurationProperties(XAuthProperties.class)
public class XAuthWebMvcConfigurer implements WebMvcConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(XAuthWebMvcConfigurer.class);

    private final XAuthProperties properties;
    private final TokenRepository tokenRepository;
    private final XAuthCheckService checkService;

    public XAuthWebMvcConfigurer(XAuthProperties properties,
                                 TokenRepository tokenRepository,
                                 XAuthCheckService checkService) {
        this.properties = properties;
        this.tokenRepository = tokenRepository;
        this.checkService = checkService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        XAuthInterceptor interceptor = new XAuthInterceptor(tokenRepository, checkService);
        registry.addInterceptor(interceptor)
                .addPathPatterns(properties.getPath())
                .excludePathPatterns(properties.getExcludePath());
        LOGGER.info(
                "[XAuthWebMvcConfigurer] register interceptor. path: [{}], excludePath: [{}].",
                properties.getPath(),
                properties.getExcludePath()
        );
    }
}
