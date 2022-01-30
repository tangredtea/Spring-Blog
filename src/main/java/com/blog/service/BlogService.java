package com.blog.service;

import com.blog.entity.Blog;

import java.util.List;
import java.util.Map;

/**
 * @author Ryan
 */
public interface BlogService {

    /**
     * 根据id查博文
     * @param id 博文id
     * @return 博文
     */
    Blog getBlog(Long id);

    /**
     * 前端展示博客
     * @param id 博文id
     * @return 博文
     */
    Blog getDetailedBlog(Long id);

    /**
     * 得到所有博文
     * @return 博文列表
     */
    List<Blog> getAllBlog();

    /**
     * 根据类型id获取博客
     * @param typeId 类型id
     * @return 博文列表
     */
    List<Blog> getByTypeId(Integer typeId);

    /**
     * 根据标签id获取博客
     * @param tagId 标签id
     * @return 博文列表
     */
    List<Blog> getByTagId(Integer tagId);

    /**
     * 主页博客展示
     * @return 博文列表
     */
    List<Blog> getIndexBlog();

    /**
     * 推荐博客展示
     * @return 博文列表
     */
    List<Blog> getAllRecommendBlog();

    /**
     * 全局搜索博客
     * @param query 关键字
     * @return 博文列表
     */
    List<Blog> getSearchBlog(String query);

    /**
     * 归档博客
     * @return 博文列表
     */
    Map<String,List<Blog>> archiveBlog();

    /**
     * 查询博客条数
     * @return 博文数目
     */
    int countBlog();

    /**
     * 保存博客
     * @param blog 博文
     * @return 状态值
     */
    int saveBlog(Blog blog);

    /**
     * 更新博客
     * @param blog 博文
     * @return 状态值
     */
    int updateBlog(Blog blog);

    /**
     * 删除博客
     * @param id 博文id
     * @return 状态值
     */
    int deleteBlog(Long id);

    /**
     * 后台根据标题、分类、推荐搜索博客
     * @param blog 博文
     * @return 博文列表
     */
    List<Blog> searchAllBlog(Blog blog);

    /**
     * 得到热门博客
     * @return 博文列表
     */
    List<Blog> getHotBlog();
}
