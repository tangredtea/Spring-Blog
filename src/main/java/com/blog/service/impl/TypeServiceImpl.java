package com.blog.service.impl;

import com.blog.dao.TypeDao;
import com.blog.entity.Type;
import com.blog.service.RedisService;
import com.blog.service.TypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ryan
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Resource
    RedisService cache;

    @Resource
    private TypeDao typeDao;

    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }

    @Override
    public Type getType(Integer id) {
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
    public int deleteType(Integer id) {
        return typeDao.deleteType(id);
    }
}
