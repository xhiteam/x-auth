package com.xhiteam.xauth.example.jwt.controller;

import com.xhiteam.xauth.core.annotation.Ignore;
import com.xhiteam.xauth.core.annotation.RequiresPermissions;
import com.xhiteam.xauth.core.annotation.RequiresRoles;
import com.xhiteam.xauth.core.model.Token;
import com.xhiteam.xauth.core.repository.TokenRepository;
import com.xhiteam.xauth.example.jwt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WanJingmiao
 * @description 用户控制类
 * @date 2020/11/14 18:27
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	TokenRepository tokenRepository;

	/**
	 * 模拟登录
	 * 通过 @Ignore注解来忽略JWT验证
	 * 下一次请求时使用Authorization - tokenStr来实现JWT认证
	 *
	 * @param user 用户的登录信息
	 * @return tokenStr
	 */
	@PostMapping("/login")
	@Ignore
	public String login(@RequestBody User user) {
		Token token = null;
		if ("张三".equals(user.getUsername()) && "123456".equals(user.getPassword())) {
			// 这里假设数据库中查询的用户id为1
			user.setId(1L);
			// 用户的权限
			ArrayList<String> permissions = new ArrayList<>();
			permissions.add("user:getAll");
			// 用户的角色
			ArrayList<String> roles = new ArrayList<>();
			roles.add("admin");
			token = tokenRepository.newToken(String.valueOf(user.getId()), roles, permissions, null);
		}
		return token.getTokenStr();
	}

	/**
	 * 模拟获取用户信息
	 * 默认开启JWT认证
	 *
	 * @param id 用户id
	 * @return 用户信息
	 */
	@GetMapping
	public User getUserInfo(Long id) {
		User user = new User();
		user.setId(id);
		user.setUsername("张三");
		return user;
	}

	/**
	 * 模拟获取所有用户信息
	 * 需要user:getAll权限
	 *
	 * @return 所有用户信息
	 */
	@GetMapping("/list")
	@RequiresPermissions({"user:getAll"})
	public List<User> getUserList() {
		ArrayList<User> users = new ArrayList<>();
		User user1 = new User();
		User user2 = new User();
		user1.setId(1L);
		user1.setUsername("张三");
		user2.setId(2L);
		user2.setUsername("李四");
		users.add(user1);
		users.add(user2);
		return users;
	}

	/**
	 * 模拟删除用户
	 * 需要admin权限
	 *
	 * @param id 用户id
	 * @return 删除的提示信息
	 */
	@DeleteMapping
	@RequiresRoles({"admin"})
	public String deleteUser(Long id) {
		return "删除成功";
	}
}
