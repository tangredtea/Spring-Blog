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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author tangredtea
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    RedisService cache;

    @Resource
    BlogDao blogDao;

    @Override
    public Blog getBlog(Long id) {
        return blogDao.getBlog(id);
    }

    @Override
    public Blog getDetailedBlog(Long id) {
        Blog blog = Optional.ofNullable(blogDao.getDetailedBlog(id))
                            .orElseThrow(() -> new NotFoundException("该博客不存在"));
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
    public List<Blog> getByTypeId(Integer typeId) {
        return blogDao.getByTypeId(typeId);
    }

    @Override
    public List<Blog> getByTagId(Integer tagId) {
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
        Map<String, List<Blog>> map = new HashMap<>(8);
        set.forEach(year -> map.put(year, blogDao.findByYear(year)));
        return map;
    }

    @Override
    public int countBlog() {
        return blogDao.getCount();
    }

    @Override
    public int getTotalViews() {
        return blogDao.getViews();
    }

    @Override
    public int getAvgViews() {
        return blogDao.getAvgViews();
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
     * 状态值
     * @param blog 博文
     * @return 保存博文
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int saveBlog(Blog blog) {
        final Date now = new Date();
        blog.setCreateTime(now);
        blog.setUpdateTime(now);
        blog.setViews(0);
        blogDao.saveBlog(blog);
        Long id = blog.getId();
        blog.getTags().forEach(tag -> {
            BlogAndTag blogAndTag = new BlogAndTag(tag.getId(), id);
            blogDao.saveBlogAndTag(blogAndTag);
        });
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        // 先删除旧的标签关联，再插入新的
        blogDao.deleteBlogAndTagByBlogId(blog.getId());
        blog.getTags().forEach(tag -> {
            BlogAndTag blogAndTag = new BlogAndTag(tag.getId(), blog.getId());
            blogDao.saveBlogAndTag(blogAndTag);
        });
        if (cache.hHasKey(RedisKey.ARTICLE, String.valueOf(blog.getId()))){
            cache.hSet(RedisKey.ARTICLE, String.valueOf(blog.getId()), blog);
        }
        return blogDao.updateBlog(blog);
    }

    @Override
    public int deleteBlog(Long id) {
        //如果缓存中有这个键值的话
        if (cache.hHasKey(RedisKey.ARTICLE_VIEWS, String.valueOf(id))){
            cache.hDel(RedisKey.ARTICLE_VIEWS, String.valueOf(id));
        }
        if (cache.hHasKey(RedisKey.ARTICLE, String.valueOf(id))){
            cache.hDel(RedisKey.ARTICLE, String.valueOf(id));
        }
        return blogDao.deleteBlog(id);
    }

}
