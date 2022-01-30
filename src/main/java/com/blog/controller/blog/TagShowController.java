package com.blog.controller.blog;

import com.blog.entity.Blog;
import com.blog.entity.Tag;
import com.blog.service.BlogService;
import com.blog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ryan
 */
@Controller
public class TagShowController {

    @Resource
    private TagService tagService;

    @Resource
    private BlogService blogService;

    @GetMapping(value = {"/tags/{id}","/tags"})
    public String types(@PathVariable(required = false) Integer id, @RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum,
                        Model model){
        //开启分页
        PageHelper.startPage(pageNum, 100);
        List<Tag> tags = tagService.getBlogTag();
        //从导航点过来的
        if (id == null){
            id = tags.get(0).getId();
        }
        List<Blog> blogs = blogService.getByTagId(id);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("tags", tags);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
