package com.blog.scheduled;

import com.blog.dao.BlogDao;
import com.blog.config.RedisKey;
import com.blog.entity.Blog;
import com.blog.service.BlogService;
import com.blog.service.MessageService;
import com.blog.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

/**
 * @author tangredtea
 */
@Slf4j
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

    /** 缓存过期时间：5小时（大于定时任务4小时间隔，保证定时任务异常时缓存也会过期） */
    private static final long CACHE_TTL = 5 * 3600;

    @PostConstruct
    public void init(){
        refreshCaches();
    }

    @Scheduled(cron = "0 0 0/4 * * ? ")
    public void execute() {
        // 博客刷新阅读量到数据库
        Map<String, Object> blogMap = cache.hGetAll(RedisKey.ARTICLE);
        Map<String, Object> viewMap = cache.hGetAll(RedisKey.ARTICLE_VIEWS);

        for (Map.Entry<String, Object> entry : blogMap.entrySet()) {
            String key = entry.getKey();
            Blog blog = (Blog) entry.getValue();
            Integer redisViews = (Integer) viewMap.get(key);

            if (redisViews != null && !redisViews.equals(blog.getViews())) {
                int increment = redisViews - blog.getViews();
                if (increment > 0) {
                    blogDao.addViews(blog.getId(), increment);
                    blog.setViews(redisViews);
                    cache.hSet(RedisKey.ARTICLE, key, blog);
                }
            }
        }
        refreshCaches();
    }

    private void refreshCaches() {
        cache.set(RedisKey.INDEXBLOG, blogService.getIndexBlog(), CACHE_TTL);
        cache.set(RedisKey.RECOMMENDBLOG, blogService.getAllRecommendBlog(), CACHE_TTL);
        cache.set(RedisKey.HOTBLOGS, blogService.getHotBlog(), CACHE_TTL);
        cache.set(RedisKey.MESSAGES, messageService.findByIndexParentId(), CACHE_TTL);
    }
}
