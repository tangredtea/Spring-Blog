package com.blog.controller.blog;

import com.blog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * @author Ryan
 */
@Controller
public class ArchiveShowController {

    @Resource
    private BlogService blogService;

    @GetMapping("/time")
    public String archives(Model model) {
        model.addAttribute("archiveMap", blogService.archiveBlog());
        model.addAttribute("blogCount", blogService.countBlog());
        return "time";
    }
}
