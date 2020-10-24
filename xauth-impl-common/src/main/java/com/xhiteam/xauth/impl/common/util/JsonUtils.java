package com.xhiteam.xauth.impl.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author WanJingmiao
 * @description json工具类
 * @date 2020/10/01 18:59
 */
public final class JsonUtils {

	private JsonUtils() {
		throw new UnsupportedOperationException("[JsonUtils] Construction is not supported");
	}

	/**
	 * 基于静态内部类的单例模式，用来获取 {@link ObjectMapper}
	 */
	public static ObjectMapper getInstance() {
		return JacksonHolder.INSTANCE;
	}
	private static class JacksonHolder {
		public static final ObjectMapper INSTANCE = new ObjectMapper();
	}

}
