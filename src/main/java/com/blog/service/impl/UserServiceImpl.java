package com.blog.service.impl;

import com.blog.dao.UserDao;
import com.blog.entity.User;
import com.blog.service.UserService;
import com.blog.util.MD5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Ryan
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;


    @Override
    public User checkUser(String username, String password) {
        return userDao.queryByUsernameAndPassword(username, MD5Utils.code(password));
    }

    @Override
    public User getUserInfoById(Integer uid) {
        return userDao.getUserInfoById(uid);
    }

    @Override
    public boolean updateUserInfo(User user, String username, String password) {
        if (checkUser(username, password) != null){
            return userDao.updateUser(user);
        }else {
            return false;
        }
    }

}
