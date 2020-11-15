[TOC]

# 1. 项目简介

## 1.1. Quick Start

1. 导入依赖

   ```xml
   <dependency>
       <groupId>com.xhiteam.xauth</groupId>
       <artifactId>xauth-client</artifactId>
       <version>0.1.0</version>
   </dependency>
   ```
2. 注入并使用`TokenRepository`

   可以将用户的角色、权限信息导入并生成相应的JWT-Token

   ```java
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
   }
   ```

3. 使用相关注解对接口进行权限校验

   默认每个接口都开启了JWT权限校验

   注解如下：

   - `@Ignore`：忽略JWT权限和登录校验
   - `@RequiresPermissions`：接口需要某些权限才能访问
   - `@RequiresRoles`：接口需要某些角色才能访问

## 1.2. 文档汇总

[设计文档](./doc/design-document.md)

# 2. Change Log

## V0.1

提供 x-auth 基础骨架，并采用 JWT 进行实现。

## V1.0（doing）

采用 JWT 实现分布式权限校验组件。主要开发任务包括：

- x-auth 客户端组件分布式调用支持
- x-auth 认证中心
- x-auth 后台管理系统