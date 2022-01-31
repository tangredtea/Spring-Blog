package com.blog.controller.blog;

import com.blog.entity.Blog;
import com.blog.entity.Type;
import com.blog.service.BlogService;
import com.blog.service.TypeService;
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
public class TypeShowController {

    @Resource
    private TypeService typeService;

    @Resource
    private BlogService blogService;

    @GetMapping(value = {"/types/{id}","/types"})
    public String types(@PathVariable(required = false) Integer id, @RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum,
                        Model model){
        //开启分页
        PageHelper.startPage(pageNum, 100);
        List<Type> types = typeService.getBlogType();
        //从导航点过来的
        if (id == null){
            id = types.get(0).getId();
            System.out.println(id);
        }
        List<Blog> blogs = blogService.getByTypeId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("types", types);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
