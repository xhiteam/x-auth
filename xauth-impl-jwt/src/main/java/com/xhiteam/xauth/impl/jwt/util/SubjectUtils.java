package com.xhiteam.xauth.impl.jwt.util;

import com.xhiteam.xauth.core.annotation.Logical;
import com.xhiteam.xauth.core.model.Token;
import com.xhiteam.xauth.impl.common.util.CollectionUtils;

import java.util.List;

/**
 * @author WanJingmiao
 * @description 认证实体工具类
 * @date 2020/09/26 11:34
 */
public class SubjectUtils {
	/**
	 * 判断是否有权限
	 *
	 * @param token       token对象
	 * @param permissions 接口需要的权限
	 * @param logical     多个权限之间的逻辑
	 * @return 是否有权限
	 */
	public static boolean hasPermission(Token token, String[] permissions, Logical logical) {
		if (token == null) {
			return false;
		}
		if (CollectionUtils.isNullOrEmpty(permissions)) {
			return true;
		}
		boolean result = false;
		List<String> tokenPermissions = token.getPermissions();
		if (!CollectionUtils.isNullOrEmpty(tokenPermissions)) {
			for (String permission : permissions) {
				result = tokenPermissions.contains(permission);
				if (logical == (result ? Logical.OR : Logical.AND)) {
					break;
				}
			}
		}
		return result;
	}

	/**
	 * 判断是否具有相应角色
	 *
	 * @param token   token对象
	 * @param roles   接口需要的角色
	 * @param logical 多个角色之间的逻辑
	 * @return 判断是否具有相应角色
	 */
	public static boolean hasRole(Token token, String[] roles, Logical logical) {
		if (token == null) {
			return false;
		}
		if (CollectionUtils.isNullOrEmpty(roles)) {
			return true;
		}
		boolean result = false;
		List<String> tokenRoles = token.getRoles();
		if (!CollectionUtils.isNullOrEmpty(tokenRoles)) {
			for (String role : roles) {
				result = tokenRoles.contains(role);
				if (logical == (result ? Logical.OR : Logical.AND)) {
					break;
				}
			}
		}
		return result;
	}
}
