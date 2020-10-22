package com.xhiteam.xauth.core.interceptor;

import com.xhiteam.xauth.core.constant.TokenConstant;
import com.xhiteam.xauth.core.exception.UnauthorizedException;
import com.xhiteam.xauth.core.model.Token;
import com.xhiteam.xauth.core.repository.TokenRepository;
import com.xhiteam.xauth.core.service.XAuthCheckService;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author lambo.chen.2306@gmail.com
 * @date 2020/9/13 20:36
 * <p>
 * 拦截器，用于 X-Auth 权限校验
 **/
@Order(1)
public class XAuthInterceptor extends HandlerInterceptorAdapter {

    final TokenRepository repository;
    final XAuthCheckService service;

    public XAuthInterceptor(TokenRepository repository, XAuthCheckService service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 开启浏览器 token Header
        response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);

        // 放行 options 请求
        if (request.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name())) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE, OPTIONS");
            response.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, String.valueOf(Token.DEFAULT_EXPIRE_S));
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Content-Type, x-requested-with, X-Custom-Header, Authorization");
            return false;
        }

        Method method = null;
        if (handler instanceof HandlerMethod) {
            method = ((HandlerMethod) handler).getMethod();
        }

        // 将Token字符串解析为Token对象
        Token token = getToken(request);

        // 判断是否忽略权限注解或用户是否具有相应权限
        if (!service.check(method,token)) {
            throw new UnauthorizedException();
        }

        // 刷新token并回传
        refreshToken(request, response);

        return super.preHandle(request, response, handler);
    }

    private void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        Token token = getToken(request);
        Token refreshToken = repository.refreshToken(token);
        if (refreshToken != null) {
            response.setHeader(TokenConstant.TOKEN, refreshToken.getTokenStr());
        }
    }

    private Token getToken(HttpServletRequest request) {
        String tokenStr = request.getHeader(HttpHeaders.AUTHORIZATION);
        return repository.parseToken(tokenStr);
    }
}
