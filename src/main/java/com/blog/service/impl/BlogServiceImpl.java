package com.blog.service.impl;

import com.blog.dao.BlogDao;
import com.blog.config.RedisKey;
import com.blog.exception.NotFoundException;
import com.blog.entity.Blog;
import com.blog.entity.BlogAndTag;
import com.blog.entity.Tag;
import com.blog.service.BlogService;
import com.blog.service.RedisService;
import com.blog.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Ryan
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    RedisService cache;

    @Autowired
    BlogDao blogDao;

    @Override
    public Blog getBlog(Long id) {
        return blogDao.getBlog(id);
    }


    @Override
    public Blog getDetailedBlog(Long id) {
        Blog blog=null;
        //如果缓存中有这个键值的话
        if (cache.hHasKey(RedisKey.ARTCILE, String.valueOf(id))){
            blog= (Blog) cache.hGet(RedisKey.ARTCILE,String.valueOf(id));
            Long aLong = cache.hIncr(RedisKey.ARTCILEVIEWS, String.valueOf(id), 1L);
            blog.setViews(Math.toIntExact(aLong));
        }
        else {
            //缓存中无的话，存储到缓存中
            blog = blogDao.getDetailedBlog(id);
            cache.hSet(RedisKey.ARTCILE,String.valueOf(id),blog);
            cache.hSet(RedisKey.ARTCILEVIEWS, String.valueOf(id),blog.getViews()+1);
        }
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = blog.getContent();
        //将Markdown格式转换成html
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return blog;
    }

    @Override
    public List<Blog> getAllBlog() {
        return blogDao.getAllBlog();
    }

    @Override
    public List<Blog> getByTypeId(Long typeId) {
        return blogDao.getByTypeId(typeId);
    }

    @Override
    public List<Blog> getByTagId(Long tagId) {
        return blogDao.getByTagId(tagId);
    }

    @Override
    public List<Blog> getIndexBlog() {
        return blogDao.getIndexBlog();
    }

    @Override
    public List<Blog> getAllRecommendBlog() {
        return blogDao.getAllRecommendBlog();
    }

    @Override
    public List<Blog> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogDao.findGroupYear();
        //set去掉重复的年份
        Set<String> set = new HashSet<>(years);  
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : set) {
            map.put(year, blogDao.findByYear(year));
        }
        return map;
    }

    @Override
    public int countBlog() {
        return blogDao.getAllBlog().size();
    }

    @Override
    public List<Blog> searchAllBlog(Blog blog) {
        return blogDao.searchAllBlog(blog);
    }

    @Override
    public List<Blog> getHotBlog() {
        return blogDao.getHotBlog();
    }

    /**
     * 新增博客
     * @param blog
     * @return
     */
    @Override    
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        //保存博客
        blogDao.saveBlog(blog);
        //保存博客后才能获取自增的id
        Long id = blog.getId();
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            //新增时无法获取自增的id,在mybatis里修改
            blogAndTag = new BlogAndTag(tag.getId(), id);
            blogDao.saveBlogAndTag(blogAndTag);
        }
        return 1;
    }

    /**
     * 编辑博客
     * @param blog
     * @return
     */
    @Override   
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            blogAndTag = new BlogAndTag(tag.getId(), blog.getId());
            blogDao.saveBlogAndTag(blogAndTag);
        }
        Long id=blog.getId();
        //如果缓存中有这个键值的话
        if (cache.hHasKey(RedisKey.ARTCILE, String.valueOf(id))){
            cache.hSet(RedisKey.ARTCILE,String.valueOf(id),blog);
        }
        return blogDao.updateBlog(blog);
    }

    @Override
    public int deleteBlog(Long id) {
        //如果缓存中有这个键值的话
        if (cache.hHasKey(RedisKey.ARTCILE, String.valueOf(id))){
            cache.hDel(RedisKey.ARTCILE,String.valueOf(id));
            cache.hDel(RedisKey.ARTCILEVIEWS,String.valueOf(id));
        }
        return blogDao.deleteBlog(id);
    }

}
