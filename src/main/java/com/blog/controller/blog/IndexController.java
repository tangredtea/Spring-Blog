package com.blog.controller.blog;

import com.blog.entity.Blog;
import com.blog.entity.Message;
import com.blog.service.BlogService;
import com.blog.service.MessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        List<Blog> allBlog = blogService.getIndexBlog();
        //获取推荐博客
        List<Blog> recommendBlog =blogService.getAllRecommendBlog();
        //得到分页结果对象
        PageInfo<? extends Blog> pageInfo = new PageInfo<>(allBlog);
        List<Message> messages = messageService.findByIndexParentId().subList(0, 8);
        model.addAttribute("messages", messages);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("recommendBlogs", recommendBlog);
        List<Blog> hotBlogs=blogService.getHotBlog();
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
    public String toLogin(@PathVariable Long id, Model model){
        Blog blog = blogService.getDetailedBlog(id);
        model.addAttribute("blog", blog);
        return "blog";
    }
}
