[TOC]

# 1. 项目简介

## 1.1. Quick Start

1. 导入依赖

   ```xml
   <dependency>
       <groupId>com.xhiteam.xauth</groupId>
       <artifactId>xauth-client</artifactId>
       <version>0.1.1</version>
   </dependency>
   ```
   
2. 注入并使用`TokenRepository`

   可以将用户的角色、权限信息导入并生成相应的JWT-Token

   - 只设置用户的id（实体的唯一标识）
   
     ```java
     token = tokenRepository.newToken(String.valueOf(user.getId()));
     ```
   
   - 设置用户的id、角色、权限
   
     ```java
     ArrayList<String> permissions = new ArrayList<>();
     permissions.add("user:getAll");
     ArrayList<String> roles = new ArrayList<>();
     roles.add("admin");
     token = tokenRepository.newToken(String.valueOf(user.getId()), roles, permissions, null);
     ```
   
   除了这些基本的设置，还可以设置token超时时间、需要token携带的额外信息等，这些都在`tokenRepository`中进行了重载。
   
3. 使用相关注解对接口进行权限校验

   默认每个接口都开启了JWT权限校验

   注解如下：

   - `@Ignore`：忽略JWT权限和登录校验
   - `@RequiresPermissions`：接口需要某些权限才能访问
   - `@RequiresRoles`：接口需要某些角色才能访问

## 1.2. 文档汇总

[设计文档](./doc/design-document.md)

# 2. Change Log

[Change Log & Version Manage](./doc/change-log.md)

## V0.1.0

提供 x-auth 基础骨架，并采用 JWT 进行实现。

## V1.0.0（doing）

采用 JWT 实现分布式权限校验组件。主要开发任务包括：

- x-auth 客户端组件分布式调用支持
- x-auth 认证中心
- x-auth 后台管理系统