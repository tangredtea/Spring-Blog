package com.blog.service;

import com.blog.pojo.Blog;

import java.util.List;
import java.util.Map;

/**
 * @author Ryan
 */
public interface BlogService {
    /**
     * 后台展示博客
     * @param id
     * @return
     */
    Blog getBlog(Long id);

    /**
     * 前端展示博客
     * @param id
     * @return
     */
    Blog getDetailedBlog(Long id);

    List<Blog> getAllBlog();

    /**
     * 根据类型id获取博客
     * @param typeId
     * @return
     */
    List<Blog> getByTypeId(Long typeId);

    /**
     * 根据标签id获取博客
     * @param tagId
     * @return
     */
    List<Blog> getByTagId(Long tagId);

    /**
     * 主页博客展示
     * @return
     */
    List<Blog> getIndexBlog();

    /**
     * 推荐博客展示
     * @return
     */
    List<Blog> getAllRecommendBlog();

    /**
     * 全局搜索博客
     * @param query
     * @return
     */
    List<Blog> getSearchBlog(String query);

    /**
     * 归档博客
     * @return
     */
    Map<String,List<Blog>> archiveBlog();

    /**
     * 查询博客条数
     * @return
     */
    int countBlog();

    /**
     * 保存博客
     * @param blog
     * @return
     */
    int saveBlog(Blog blog);

    /**
     * 更新博客
     * @param blog
     * @return
     */
    int updateBlog(Blog blog);

    /**
     * 删除博客
     * @param id
     * @return
     */
    int deleteBlog(Long id);

    /**
     * 后台根据标题、分类、推荐搜索博客
     * @param blog
     * @return
     */
    List<Blog> searchAllBlog(Blog blog);

    /**
     * 得到热门博客
     * @return
     */
    List<Blog> getHotBlog();
}
