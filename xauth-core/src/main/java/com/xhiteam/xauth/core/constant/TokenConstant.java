package com.xhiteam.xauth.core.constant;

/**
 * @author WanJingmiao
 * @description Token的相关常量
 * @date 2020/09/26 16:16
 */
public interface TokenConstant {

	/**
	 * token 载荷名称
	 */
	String TOKEN = "token";

	/**
	 * key 私有保存 【万不可泄露，否则其他人也可签发 token】
	 */
	String PRIVATE_KEY = "qwertyuiop;'jhgfd.,/;l/;lsxcvbn1@#Q$YIFIS#%^&*>.cv%%#$dfg234567890#$%^&*(rtyujk#$%^&*(,," +
			"?<>?sfgdg3423456.'./'/{||}.;fdghj,nl;bncjk)(*834df3ywer$&*Y(GJBFDS:{}:Hfgdhgfhng3457DIFH";

}
