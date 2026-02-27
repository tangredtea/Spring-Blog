package com.blog.service.impl;

import com.blog.dao.UserDao;
import com.blog.entity.User;
import com.blog.service.UserService;
import com.blog.util.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tangredtea
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;


    @Override
    public User checkUser(String username, String password) {
        return userDao.queryByUsernameAndPassword(username, MD5Utils.code(password));
    }

    @Override
    public User getUserInfoById(Integer id) {
        return userDao.getUserInfoById(id);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getAllUser();
    }

    @Override
    public int deleteUser(Integer id) {
        return userDao.deleteUser(id);
    }

    @Override
    public int getUserInfoByUsername(String name) {
        return userDao.getUserInfoByUsername(name);
    }

}
