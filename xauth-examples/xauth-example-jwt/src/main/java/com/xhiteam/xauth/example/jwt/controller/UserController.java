package com.xhiteam.xauth.example.jwt.controller;

import com.xhiteam.xauth.core.annotation.Ignore;
import com.xhiteam.xauth.core.model.Token;
import com.xhiteam.xauth.core.repository.TokenRepository;
import com.xhiteam.xauth.example.jwt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	 * 登录示例
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
			token = tokenRepository.newToken(String.valueOf(user.getId()));
		}
		return token.getTokenStr();
	}

	/**
	 * 获取用户信息
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
}
