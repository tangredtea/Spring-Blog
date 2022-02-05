package com.blog.scheduled;

import com.blog.dao.BlogDao;
import com.blog.config.RedisKey;
import com.blog.entity.Blog;
import com.blog.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
    RedisService cache;

    @Scheduled(cron = "0 0 0/4 * * ? ")
    public void execute() {
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
    }
}
