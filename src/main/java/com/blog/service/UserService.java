package com.blog.service;

import com.blog.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * @param id   主键
     * @return user
     */
    User getUserInfoById(Integer id);

    /**
     * 更改用户信息
     * @param user  user对象
     * @return 状态值
     */
    int updateUser(User user);

    /**
     * 更改用户信息
     * @param user  user对象
     * @return 状态值
     */
    int saveUser(User user);

    /**
     * 得到所有用户
     * @return 用户
     */
    List<User> getUsers();

    /**
     * 删除用户
     * @param id 用户id
     * @return 状态值
     */
    int deleteUser(Integer id);

    /**
     * 查询用户名数量
     * @param name
     * @return 数量
     */
    int getUserInfoByUsername(String name);
}
