package com.xhiteam.xauth.core.constant;

/**
 * @author WanJingmiao
 * @description
 * @date 2020/09/26 16:16
 */
public interface TokenConstant {

	/**
	 * tokenKey 存储 key
	 */
	String KEY_TOKEN_KEY = "oauth_token_key";

	/**
	 * token 存储 key 前缀
	 */
	String KEY_PREFIX_TOKEN = "oauth_token:";

	/**
	 * 角色存储 key 前缀
	 */
	String KEY_PREFIX_ROLE = "oauth_role";

	/**
	 * 权限存储 key 前缀
	 */
	String KEY_PREFIX_PERMISSION = "oauth_perm";

	/**
	 * token 载荷名称
	 */
	String TOKEN = "token";

	/**
	 * token 名称
	 */
	String TOKEN_NAME = TokenConstant.TOKEN;

	/**
	 * http 存储 token 名称
	 */
	String HTTP_TOKEN = TokenConstant.TOKEN;

	/**
	 * key 私有保存 【万不可泄露，否则其他人也可签发 token】
	 */
	String PRIVATE_KEY = "qwertyuiop;'jhgfd.,/;l/;lsxcvbn1@#Q$YIFIS#%^&*>.cv%%#$dfg234567890#$%^&*(rtyujk#$%^&*(,," +
			"?<>?sfgdg3423456.'./'/{||}.;fdghj,nl;bncjk)(*834df3ywer$&*Y(GJBFDS:{}:Hfgdhgfhng3457DIFH";

	/**
	 * token header 名称
	 */
	String HEADER_NAME_TOKEN = "Authorization";
}
