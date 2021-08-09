package com.blog.service.impl;

import com.blog.dao.TypeDao;
import com.blog.pojo.Tag;
import com.blog.pojo.Type;
import com.blog.service.RedisService;
import com.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Ryan
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    RedisService cache;

    @Autowired
    private TypeDao typeDao;

    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }

    @Override
    public Type getType(Long id) {
        return typeDao.getType(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Override
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }


    @Override
    public List<Type> getBlogType() {
        return typeDao.getBlogType();
    }

    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    @Override
    public int deleteType(Long id) {
        return typeDao.deleteType(id);
    }
}
