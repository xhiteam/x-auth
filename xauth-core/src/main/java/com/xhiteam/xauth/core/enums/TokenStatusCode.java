package com.xhiteam.xauth.core.enums;

/**
 * @author WanJingmiao
 * @description token状态码
 * @date 2020/11/19 23:11
 */
public enum TokenStatusCode {

	/**
	 * token错误
	 */
	ERROR("0001", "Token is wrong"),

	/**
	 * token超时
	 */
	EXPIRED("0002", "Token has expired"),

	/**
	 * token不存在
	 */
	NON_TOKEN("0003", "Token is not exists"),

	/**
	 * 未授权
	 */
	UNAUTHORIZED("0004", "Unauthorized")

	;

	/**
	 * 状态码
	 */
	private String code;

	/**
	 * 状态信息
	 */
	private String msg;

	TokenStatusCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	void setMsg(String msg) {
		this.msg = msg;
	}
}
