package com.blog.dao;

import com.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Ryan
 */
@Mapper
@Repository
public interface UserDao {
    /**
     * 查询用户登录
     * @param username 账号
     * @param password 密码
     * @return user
     */
    User queryByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 通过id查询用户信息
     * @param userId 用户id
     * @return user
     */
    User getUserInfoById(Integer userId);

    /**
     * 修改用户信息
     * @param user 用户
     * @return boolean
     */
    boolean updateUser(User user);
}
