package com.blog.controller;

import com.blog.pojo.Blog;
import com.blog.pojo.Comment;
import com.blog.pojo.Tag;
import com.blog.pojo.Type;
import com.blog.service.BlogService;
import com.blog.service.CommentService;
import com.blog.service.TagService;
import com.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private CommentService commentService;

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
        List<Blog> recommendBlog =blogService.getAllRecommendBlog();  //获取推荐博客
        List<Comment> comment=commentService.findComment();
        //得到分页结果对象
        PageInfo pageInfo = new PageInfo(allBlog);
        System.out.println(comment.toString());
        model.addAttribute("comments",comment);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("recommendBlogs", recommendBlog);
        List<Blog> HotBlog=blogService.getHotBlog();
        model.addAttribute("HotBlog", HotBlog);
        return "index";
//        List<Type> allType = typeService.getBlogType();  //获取博客的类型(联表查询)
//        List<Tag> allTag = tagService.getBlogTag();  //获取博客的标签(联表查询)
//        model.addAttribute("tags", allTag);
//        model.addAttribute("types", allType);
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
        System.out.println(blog);
        return "blog";
    }
}
