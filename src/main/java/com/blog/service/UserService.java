package com.blog.service;

import com.blog.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author Ryan
 */
public interface UserService {

    /**
     * 登录
     * @param username 账号
     * @param password 密码
     * @return user
     */
    User checkUser(@Param("username") String username, @Param("password") String password);

    /**
     * 通过用户ID获取用户信息
     * @param uid   主键
     * @return user
     */
    User getUserInfoById(Integer uid);

    /**
     * 更改用户信息
     * @param user  user对象
     * @param username 原账号
     * @param password 原密码
     * @return bool
     */
    boolean updateUserInfo(User user, String username, String password);
}
