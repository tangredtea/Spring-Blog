package com.blog.dao;

import com.blog.entity.Blog;
import com.blog.entity.BlogAndTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Ryan
 */
@Mapper
@Repository
public interface BlogDao {
    /**
     * 后台展示博客
     * @param id 博客id
     * @return 博文
     */
    Blog getBlog(Long id);

    /**
     *  博客详情
     * @param id 博客id
     * @return 博文
     */
    Blog getDetailedBlog(@Param("id") Long id);

    /**
     * 得到所有博客
     * @return 博客列表
     */
    List<Blog> getAllBlog();

    /**
     * 文章访问量
     * @param id 博客id
     * @param values 偏移量
     * @return 修改状态
     */
    int updateViews(Long id,int values);

    /**
     * 根据类型id获取博客
     * @param typeId 类型id
     * @return 博客列表
     */
    List<Blog> getByTypeId(Integer typeId);

    /**
     * 根据标签id获取博客
     * @param tagId 标签id
     * @return 博客列表
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
     * 后台根据标题、分类、推荐搜索博客
     * @param blog 博文
     * @return 博文列表
     */
    List<Blog> searchAllBlog(Blog blog);

    /**
     * 查询所有年份，返回一个集合
     * @return 日期值
     */
    List<String> findGroupYear();

    /**
     *  按年份查询博客
     * @param year 年份
     * @return 博文列表
     */
    List<Blog> findByYear(@Param("year") String year);

    /**
     * 保存博客
     * @param blog 博文
     * @return 状态值
     */
    int saveBlog(Blog blog);

    /**
     * 保存博客和标签
     * @param blogAndTag 博文和标签
     * @return 状态值
     */
    int saveBlogAndTag(BlogAndTag blogAndTag);

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
     * 热门博客推荐
     * @return 博文列表
     */
    List<Blog> getHotBlog();

    /**
     * 得到博文数量
     * @return 数量
     */
    int getCount();

    /**
     * 得到阅读数
     * @return 阅读量
     */
    int getViews();

    /**
     * 得到平均阅读数
     * @return 阅读量
     */
    int getAvgViews();
}
