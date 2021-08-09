package com.blog.controller;

import com.blog.pojo.Blog;
import com.blog.pojo.Message;
import com.blog.service.BlogService;
import com.blog.service.MessageService;
import com.blog.service.TagService;
import com.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ryan
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private TagService tagService;

    /**
     * 首页数据
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping("/")
    public String toIndex(@RequestParam(required = false,defaultValue = "1") int pageNum,Model model){
        PageHelper.startPage(pageNum, 5);
        List<Blog> allBlog = blogService.getIndexBlog();
        //获取推荐博客
        List<Blog> recommendBlog =blogService.getAllRecommendBlog();
        //得到分页结果对象
        PageInfo pageInfo = new PageInfo(allBlog);
        List<Message> messages = messageService.findByIndexParentId();
        model.addAttribute("messages", messages);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("recommendBlogs", recommendBlog);
        List<Blog> HotBlog=blogService.getHotBlog();
        model.addAttribute("HotBlog", HotBlog);
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                         @RequestParam String query, Model model){

        PageHelper.startPage(pagenum, 5);
        List<Blog> searchBlog = blogService.getSearchBlog(query);
        PageInfo pageInfo = new PageInfo(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String toLogin(@PathVariable Long id, Model model){
        Blog blog = blogService.getDetailedBlog(id);
        model.addAttribute("blog", blog);
        return "blog";
    }
}
