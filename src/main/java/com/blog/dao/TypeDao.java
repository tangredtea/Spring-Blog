package com.blog.dao;

import com.blog.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ryan
 */
@Mapper
@Repository
public interface TypeDao {
    /**
     * 保存分类
     * @param type
     * @return
     */
    int saveType(Type type);

    /**
     * 得到分类
     * @param id
     * @return
     */
    Type getType(Long id);

    /**
     * 通过分类名搜索
     * @param name
     * @return
     */
    Type getTypeByName(String name);

    /**
     * 得到所有分类
     * @return
     */
    List<Type> getAllType();

    /**
     * 首页右侧展示type对应的博客数量
     * @return
     */
    List<Type> getBlogType();

    /**
     * 更新分类
     * @param type
     * @return
     */
    int updateType(Type type);

    /**
     * 删除分类
     * @param id
     * @return
     */
    int deleteType(Long id);
}
