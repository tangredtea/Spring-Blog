package com.blog.service;

import com.blog.entity.Type;

import java.util.List;

/**
 * @author Ryan
 */
public interface TypeService {
    /**
     * 保存分类
     * @param type 分类
     * @return 状态值
     */
    int saveType(Type type);

    /**
     * 得到分类名
     * @param id 分类id
     * @return 分类
     */
    Type getType(Integer id);

    /**
     * 通过名字得到分类名
     * @param name 分类名
     * @return 分类
     */
    Type getTypeByName(String name);

    /**
     * 得到所有分类
     * @return 分类列表
     */
    List<Type> getAllType();

    /**
     * 首页右侧展示type对应的博客数量
     * @return 分类列表
     */
    List<Type> getBlogType();

    /**
     * 更新分类
     * @param type 分类
     * @return 状态值
     */
    int updateType(Type type);

    /**
     * 删除分类
     * @param id 分类id
     * @return 状态值
     */
    int deleteType(Integer id);
}
