/**
 * @author lambo.chen.2306@gmail.com
 * @date 2020/11/1 22:01
 * @since version 1.0
 *
 * 此 module 主要进行 JWT 实现 x-auth 功能的认证中心、后台管理平台研发。
 *
 * 【认证中心】RESTful API:
 * 1. token 签发
 * 2. token 解析
 * 3. token 刷新
 *
 * 【后台管理】
 * 1. token 管理（查看，删除）
 *
 * xauth token 机制
 * access_token: xauth客户端存储
 * data_token：服务端存储
 *
 **/
package com.xhiteam.xauth.server.jwt;