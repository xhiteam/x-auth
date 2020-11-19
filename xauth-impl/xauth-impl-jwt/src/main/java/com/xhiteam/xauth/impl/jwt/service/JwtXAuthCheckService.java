package com.xhiteam.xauth.impl.jwt.service;

import com.xhiteam.xauth.core.annotation.Ignore;
import com.xhiteam.xauth.core.annotation.RequiresPermissions;
import com.xhiteam.xauth.core.annotation.RequiresRoles;
import com.xhiteam.xauth.core.model.Token;
import com.xhiteam.xauth.core.service.XAuthCheckService;
import com.xhiteam.xauth.impl.jwt.util.SubjectUtils;

import java.lang.reflect.Method;

/**
 * @author WanJingmiao
 * @description XAuth业务方法实现类
 * @date 2020/10/17 22:44
 */
public class JwtXAuthCheckService implements XAuthCheckService {

	@Override
	public boolean check(Method method, Token token) {
		if (checkIgnore(method)) {
			return true;
		}
		return checkPermission(method, token) && checkRole(method, token);
	}

	@Override
	public boolean checkIgnore(Method method) {
		Ignore annotation = method.getAnnotation(Ignore.class);
		if (annotation == null) {
			// 如果当前method上无注解，从类上拿
			annotation = method.getDeclaringClass().getAnnotation(Ignore.class);
		}
		return annotation != null;
	}

	@Override
	public boolean checkPermission(Method method, Token token) {
		if (token == null) {
			return false;
		}
		RequiresPermissions annotation = method.getAnnotation(RequiresPermissions.class);
		if (annotation == null) {
			// 如果当前method上无注解，从类上拿
			annotation = method.getDeclaringClass().getAnnotation(RequiresPermissions.class);
		}
		if (annotation == null) {
			return true;
		}
		return SubjectUtils.hasPermission(token, annotation.value(), annotation.logical());
	}

	@Override
	public boolean checkRole(Method method, Token token) {
		if (token == null) {
			return false;
		}
		RequiresRoles annotation = method.getAnnotation(RequiresRoles.class);
		if (annotation == null) {
			annotation = method.getDeclaringClass().getAnnotation(RequiresRoles.class);
		}
		if (annotation == null) {
			return true;
		}
		return SubjectUtils.hasRole(token, annotation.value(), annotation.logical());
	}
}
