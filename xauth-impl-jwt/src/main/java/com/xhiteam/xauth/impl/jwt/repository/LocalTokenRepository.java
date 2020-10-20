package com.xhiteam.xauth.impl.jwt.repository;

import com.xhiteam.xauth.core.model.Token;
import com.xhiteam.xauth.core.repository.AbstractTokenRepository;
import com.xhiteam.xauth.impl.jwt.util.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @author WanJingmiao
 * @description
 * @date 2020/10/16 11:02
 */
public class LocalTokenRepository extends AbstractTokenRepository {
	private static Logger log = LogManager.getLogger(LocalTokenRepository.class);


	@Override
	public Token parseToken(String tokenStr) {
		if (StringUtils.isNotBlank(tokenStr)) {
			Token token = TokenUtils.parseToken(tokenStr);
			token.setTokenStr(tokenStr);
			return token;
		}
		return null;
	}

	@Override
	public Token newToken(String id, List<String> roles, List<String> permissions, Calendar expire, Map<String, String> extensions) {
		return TokenUtils.buildToken(id, roles, permissions, expire, extensions);
	}

	@Override
	public Token newToken(Token token) {
		return TokenUtils.buildToken(token.getId(), token.getRoles(), token.getPermissions(), token.getExtensions());
	}

	@Override
	public Token refreshToken(Token token) {
		if (token == null || token.getExpire() == null) {
			log.error("LocalTokenRepository#newToken: illegal param. token={}", token);
			return null;
		} else if (token.getExpire().getTimeInMillis() < System.currentTimeMillis()) {
			log.error("LocalTokenRepository#newToken: token has expired. token={}", token);
			return null;
		}
		return this.newToken(token);
	}
}
