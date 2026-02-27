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
 * @author tangredtea
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
        Map<String, Object> blog_map = cache.hGetAll(RedisKey.ARTCILE);
        Map<String, Object> view_map = cache.hGetAll(RedisKey.ARTCILEVIEWS);
        
        for (Map.Entry<String, Object> entry : blog_map.entrySet()) {
            String key = entry.getKey();
            Blog blog = (Blog) entry.getValue();
            Integer redisViews = (Integer) view_map.get(key);
            
            if (redisViews != null && !redisViews.equals(blog.getViews())) {
                // 计算增量并原子更新
                int increment = redisViews - blog.getViews();
                if (increment > 0) {
                    // 这里需要批量更新或循环调用 incrementViews
                    for (int i = 0; i < increment; i++) {
                        blogDao.incrementViews(blog.getId());
                    }
                    blog.setViews(redisViews);
                    cache.hSet(RedisKey.ARTCILE, key, blog);
                }
            }
        }
        // 更新首页推荐博客、博文、留言
        cache.set(RedisKey.INDEXBLOG, blogService.getIndexBlog());
        cache.set(RedisKey.RECOMMENDBLOG, blogService.getAllRecommendBlog());
        cache.set(RedisKey.HOTBLOGS, blogService.getHotBlog());
        cache.set(RedisKey.MESSAGES, messageService.findByIndexParentId());
    }
}
