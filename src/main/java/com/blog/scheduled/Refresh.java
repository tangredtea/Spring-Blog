package com.blog.scheduled;

import com.blog.dao.BlogDao;
import com.blog.config.RedisKey;
import com.blog.dao.MessageDao;
import com.blog.entity.Blog;
import com.blog.service.BlogService;
import com.blog.service.MessageService;
import com.blog.service.RedisService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Ryan
 */
@Component
public class Refresh {

    @Resource
    BlogDao blogDao;

    @Resource
    BlogService blogService;

    @Resource
    MessageService messageService;

    @Resource
    RedisService cache;

    @PostConstruct
    public void init(){
        // 更新首页推荐博客、博文、留言
        cache.set(RedisKey.INDEXBLOG, blogService.getIndexBlog());
        cache.set(RedisKey.RECOMMENDBLOG, blogService.getAllRecommendBlog());
        cache.set(RedisKey.MESSAGES, messageService.findByIndexParentId());
        cache.set(RedisKey.HOTBLOGS, blogService.getHotBlog());
    }

    @Scheduled(cron = "0 0 0/4 * * ? ")
    public void execute() {
        // 博客刷新阅读量到数据库
        Map blog_map = cache.hGetAll(RedisKey.ARTCILE);
        Map view_map = cache.hGetAll(RedisKey.ARTCILEVIEWS);
        for (Object o : blog_map.keySet()) {
            Integer i= (Integer) o;
            Object o1 = view_map.get(i);
            Blog blog= (Blog) o1;
            if (blog.getViews() != view_map.get(i)){
                blogDao.updateViews(blog.getId(), (Integer) o1);
                blog.setViews((Integer) o1);
                cache.hSet(RedisKey.ARTCILE, String.valueOf(blog.getId()), blog);
            }
        }
        // 更新首页推荐博客、博文、留言
        cache.set(RedisKey.INDEXBLOG, blogService.getIndexBlog());
        cache.set(RedisKey.RECOMMENDBLOG, blogService.getAllRecommendBlog());
        cache.set(RedisKey.HOTBLOGS, blogService.getHotBlog());
        cache.set(RedisKey.MESSAGES, messageService.findByIndexParentId());
    }
}
