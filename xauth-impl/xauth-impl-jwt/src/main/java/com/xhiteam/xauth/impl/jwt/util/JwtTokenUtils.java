package com.xhiteam.xauth.impl.jwt.util;

import com.xhiteam.xauth.core.constant.TokenConstant;
import com.xhiteam.xauth.core.model.Token;
import com.xhiteam.xauth.core.model.TokenImpl;
import com.xhiteam.xauth.impl.common.util.JsonUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.*;

/**
 * @author WanJingmiao
 * @description token工具类
 * @date 2020/09/26 14:24
 */
public final class JwtTokenUtils {

	private JwtTokenUtils() {
		throw new UnsupportedOperationException("[JwtTokenUtils] Construction is not supported");
	}

	private static Logger log = LogManager.getLogger(JwtTokenUtils.class);

	/**
	 * 构建token
	 *
	 * @param subject 实体标识
	 * @return token对象
	 */
	public static Token buildToken(String subject) {
		return buildToken(subject, null, null);
	}

	/**
	 * 构建token
	 *
	 * @param subject     实体标识
	 * @param roles       角色
	 * @param permissions 权限
	 * @return token对象
	 */
	public static Token buildToken(String subject, List<String> roles, List<String> permissions) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MILLISECOND, Token.DEFAULT_EXPIRE_S);
		return buildToken(subject, roles, permissions, cal);
	}

	/**
	 * 构建token
	 *
	 * @param subject     实体标识
	 * @param roles       角色
	 * @param permissions 权限
	 * @param extensions  扩展信息
	 * @return token对象
	 */
	public static Token buildToken(String subject, List<String> roles, List<String> permissions,
								   Map<String, String> extensions) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MILLISECOND, Token.DEFAULT_EXPIRE_S);
		return buildToken(subject, roles, permissions, cal, extensions);
	}

	/**
	 * 构建token
	 *
	 * @param subject     实体标识
	 * @param roles       角色
	 * @param permissions 权限
	 * @param expire      过期时间
	 * @return token对象
	 */
	public static Token buildToken(String subject, List<String> roles,
								   List<String> permissions, Calendar expire) {
		return buildToken(subject, roles, permissions, expire, Token.DEFAULT_EXPIRE_S);
	}

	/**
	 * 构建token
	 *
	 * @param subject     实体标识
	 * @param roles       角色
	 * @param permissions 权限
	 * @param expire      过期时间
	 * @param extensions  扩展信息
	 * @return token对象
	 */
	public static Token buildToken(String subject, List<String> roles,
								   List<String> permissions, Calendar expire,
								   Map<String, String> extensions) {
		return buildToken(subject, roles, permissions, expire, Token.DEFAULT_EXPIRE_S, extensions);
	}

	/**
	 * 构建token
	 *
	 * @param subject     实体标识
	 * @param roles       角色
	 * @param permissions 权限
	 * @param expire      过期时间
	 * @param ttl         生存周期
	 * @return token对象
	 */
	public static Token buildToken(String subject, List<String> roles, List<String> permissions,
								   Calendar expire, int ttl) {
		return buildToken(subject, roles, permissions, expire, ttl, null);
	}

	/**
	 * 构建token
	 *
	 * @param subject     实体标识
	 * @param roles       角色
	 * @param permissions 权限
	 * @param expire      过期时间
	 * @param ttl         生存周期
	 * @param extensions  扩展信息
	 * @return token对象
	 */
	public static Token buildToken(String subject, List<String> roles, List<String> permissions,
								   Calendar expire, int ttl, Map<String, String> extensions) {
		return buildToken(subject, roles, permissions, expire, ttl, extensions, generateKey());
	}

	/**
	 * 构建token
	 *
	 * @param subject     实体标识
	 * @param roles       角色
	 * @param permissions 权限
	 * @param expire      过期时间
	 * @param ttl         生存周期
	 * @param extensions  扩展信息
	 * @param key         生成token需要的key
	 * @return token对象
	 */
	public static Token buildToken(String subject, List<String> roles, List<String> permissions,
								   Calendar expire, int ttl, Map<String, String> extensions, Key key) {
		//判断参数合法性
		if (StringUtils.isEmpty(subject)
				|| expire == null
				|| expire.getTimeInMillis() <= System.currentTimeMillis()
				|| key == null) {
			return null;
		}
		//构建token内容
		Token result = new TokenImpl()
				.setId(subject)
				.setRoles(roles)
				.setPermissions(permissions)
				.setExpire(expire)
				.setTtl(ttl)
				.setExtensions(extensions);
		//构建载荷
		Map<String, Object> claims = new HashMap<>(16);
		claims.put(TokenConstant.TOKEN, result);
		//构建token字符串
		String tokenStr = Jwts.builder()
				.setClaims(claims)
				.setId(UUID.randomUUID().toString())
				.setIssuedAt(new Date())
				.setIssuer("framework")
				.setHeaderParam("typ", "JWT")
				.setSubject(subject)
				.signWith(key, SignatureAlgorithm.HS512)
				.setExpiration(expire.getTime())
				.compact();
		result.setTokenStr(tokenStr);
		return result;
	}

	/**
	 * 解析Token字符串为Token对象
	 *
	 * @param token token字符串JSON
	 * @return
	 */
	public static Token parseToken(String token) {
		// 获取载荷
		Claims claims = parse(token);
		Token tokenBean = null;
		if (claims != null) {
			try {
				tokenBean = JsonUtils.getInstance().convertValue(claims.get(TokenConstant.TOKEN), TokenImpl.class);
			} catch (Exception e) {
				log.error("JwtTokenUtils#parseToken: token={}", token);
			}
		}
		return tokenBean;
	}

	/**
	 * 解析token，获取载荷
	 *
	 * @param token token字符串
	 * @return 载荷对象
	 */
	private static Claims parse(String token) {
		if (StringUtils.isEmpty(token)) {
			return null;
		}
		try {
			return Jwts.parser().setSigningKey(generateKey()).parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException e) {
			log.error("JwtTokenUtils#parse: Token has expired. token={}", token);
		} catch (UnsupportedJwtException e) {
			log.error("JwtTokenUtils#parse: Unsupported token. token={}", token);
		} catch (SignatureException e) {
			log.error("JwtTokenUtils#parse: Wrong key. token={}", token);
		} catch (IllegalArgumentException e) {
			log.error("JwtTokenUtils#parse: The jwt is null. token={}", token);
		} catch (MalformedJwtException e) {
			log.error("JwtTokenUtils#parse: Token Construction error. token={}", token);
		} catch (Exception e) {
			log.error("JwtTokenUtils#parse: Parsing token claim error. token={}", token);
		}
		return null;
	}

	/**
	 * 生成密钥
	 *
	 * @return 密钥
	 */
	private static Key generateKey() {
		return Keys.hmacShaKeyFor(TokenConstant.PRIVATE_KEY.getBytes());
	}
}
