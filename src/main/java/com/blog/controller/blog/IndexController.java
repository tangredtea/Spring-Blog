package com.blog.controller.blog;

import com.blog.config.RedisKey;
import com.blog.entity.Blog;
import com.blog.entity.Message;
import com.blog.service.BlogService;
import com.blog.service.MessageService;
import com.blog.service.RedisService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ryan
 */
@Controller
public class IndexController {

    @Resource
    private RedisService cache;

    @Resource
    private BlogService blogService;

    @Resource
    private MessageService messageService;

    /**
     * 首页数据
     * @param model 视图
     * @param pageNum 分页
     * @return 渲染视图
     */
    @RequestMapping("/")
    public String toIndex(@RequestParam(required = false,defaultValue = "1") int pageNum,Model model){
        PageHelper.startPage(pageNum, 5);
        List<Blog> allBlog;
        List<Blog> recommendBlog;
        List<Message> messages;
        List<Blog> hotBlogs;
        if (cache.hasKey(RedisKey.INDEXBLOG)){
            allBlog = (List<Blog>) cache.get(RedisKey.INDEXBLOG);
        }else {
            allBlog = blogService.getIndexBlog();
            cache.set(RedisKey.INDEXBLOG, allBlog);
        }
        //获取推荐博客
        if (cache.hasKey(RedisKey.RECOMMENDBLOG)){
            recommendBlog = (List<Blog>) cache.get(RedisKey.RECOMMENDBLOG);
        }else {
            recommendBlog = blogService.getAllRecommendBlog();
            cache.set(RedisKey.RECOMMENDBLOG, recommendBlog);
        }

        if (cache.hasKey(RedisKey.MESSAGES)){
            messages = (List<Message>) cache.get(RedisKey.MESSAGES);
        }else {
            messages = messageService.findByIndexParentId();
            cache.set(RedisKey.RECOMMENDBLOG, messages);
        }
        if (cache.hasKey(RedisKey.HOTBLOGS)){
            hotBlogs = (List<Blog>) cache.get(RedisKey.HOTBLOGS);
        }else {
            hotBlogs = blogService.getHotBlog();
            cache.set(RedisKey.HOTBLOGS, hotBlogs);
        }
        if (messages.size() >= 8){
            messages = messages.subList(0, 8);
        }
        //得到分页结果对象
        PageInfo<? extends Blog> pageInfo = new PageInfo<>(allBlog);
        model.addAttribute("messages", messages);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("recommendBlogs", recommendBlog);
        model.addAttribute("hotBlogs", hotBlogs);
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum,
                         @RequestParam String query, Model model){

        PageHelper.startPage(pageNum, 5);
        List<Blog> searchBlog = blogService.getSearchBlog(query);
        PageInfo<? extends Blog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String getBlog(@PathVariable Long id, Model model){
        Blog blog;
        if (cache.hHasKey(RedisKey.ARTCILE, String.valueOf(id))){
            blog = (Blog) cache.hGet(RedisKey.ARTCILE, String.valueOf(id));
        }else {
            blog = blogService.getDetailedBlog(id);
            cache.hSet(RedisKey.ARTCILE, String.valueOf(id), blog);
        }
        if (!cache.hHasKey(RedisKey.ARTCILEVIEWS, String.valueOf(id))){
            cache.hSet(RedisKey.ARTCILEVIEWS, String.valueOf(id), blog.getViews());
        }
        cache.hIncr(RedisKey.ARTCILEVIEWS, String.valueOf(id), 1L);
        blog.setViews((Integer) cache.hGet(RedisKey.ARTCILEVIEWS, String.valueOf(id)));
        model.addAttribute("blog", blog);
        return "blog";
    }
}
