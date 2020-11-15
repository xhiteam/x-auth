package com.xhiteam.xauth.impl.jwt.repository;

import com.xhiteam.xauth.core.model.Token;
import com.xhiteam.xauth.core.repository.AbstractTokenRepository;
import com.xhiteam.xauth.impl.jwt.util.JwtTokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @author WanJingmiao
 * @description Token仓库的实现类
 * @date 2020/10/16 11:02
 */
public class JwtTokenRepository extends AbstractTokenRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenRepository.class);

	@Override
	public Token parseToken(String tokenStr) {
		if (!StringUtils.isEmpty(tokenStr)) {
			Token token = JwtTokenUtils.parseToken(tokenStr);
			token.setTokenStr(tokenStr);
			return token;
		}
		return null;
	}

	@Override
	public Token newToken(String id, List<String> roles, List<String> permissions, Calendar expire, Map<String, String> extensions) {
		return JwtTokenUtils.buildToken(id, roles, permissions, expire, extensions);
	}

	@Override
	public Token newToken(Token token) {
		return JwtTokenUtils.buildToken(token.getId(), token.getRoles(), token.getPermissions(), token.getExtensions());
	}

	@Override
	public Token refreshToken(Token token) {
		if (token == null) {
			return null;
		} else if(token.getExpire() == null) {
			LOGGER.error("JwtTokenRepository#refreshToken: illegal param. token={}", token);
			return null;
		} else if (token.getExpire().getTimeInMillis() < System.currentTimeMillis()) {
			LOGGER.error("JwtTokenRepository#refreshToken: token has expired. token={}", token);
			return null;
		}
		return this.newToken(token);
	}
}
