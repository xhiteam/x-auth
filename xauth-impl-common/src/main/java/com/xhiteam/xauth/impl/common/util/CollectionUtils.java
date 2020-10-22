package com.xhiteam.xauth.impl.common.util;

import java.util.Collection;
import java.util.Objects;

/**
 * @author WanJingmiao
 * @description 集合工具类
 * @date 2020/09/27 22:28
 */
public final class CollectionUtils {

	/**
	 * 集合判空
	 *
	 * @param collection 集合类
	 * @return 集合是否为空
	 */
	public static boolean isNullOrEmpty(final Collection<?> collection) {
		return Objects.isNull(collection) || collection.isEmpty();
	}

	/**
	 * 数组判空
	 *
	 * @param array 数组
	 * @param <T>   类型
	 * @return 数组是否为空
	 */
	public static <T> boolean isNullOrEmpty(final T[] array) {
		return Objects.isNull(array) || array.length <= 0;
	}
}
