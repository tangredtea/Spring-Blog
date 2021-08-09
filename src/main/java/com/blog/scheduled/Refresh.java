package com.blog.scheduled;

import com.blog.dao.BlogDao;
import com.blog.entity.RedisKey;
import com.blog.pojo.Blog;
import com.blog.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Ryan
 */
@Component
public class Refresh {

    @Autowired
    BlogDao blogDao;

    @Autowired
    RedisService cache;

    @Scheduled(cron = "0 0 0/4 * * ? ")
    public void execute() {
        Map map = cache.hGetAll(RedisKey.ARTCILE);
        Map map1 = cache.hGetAll(RedisKey.ARTCILEVIEWS);
        for (Object o : map.keySet()) {
            Integer i= (Integer) o;
            Object o1 = map.get(i);
            Blog blog= (Blog) o1;
            if (blog.getViews()!=map1.get(i)){

            }
        }
    }
}
