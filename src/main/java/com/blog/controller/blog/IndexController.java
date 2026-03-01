package com.blog.controller.blog;

import com.blog.config.RedisKey;
import com.blog.entity.Blog;
import com.blog.entity.Message;
import com.blog.service.AIService;
import com.blog.service.BlogService;
import com.blog.service.MessageService;
import com.blog.service.RedisService;
import com.blog.service.SmartSearchService;
import com.blog.util.CommonResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author tangredtea
 */
@Controller
public class IndexController {

    /** 缓存过期时间：5小时 */
    private static final long CACHE_TTL = 5 * 3600;

    @Resource
    private RedisService cache;

    @Resource
    private BlogService blogService;

    @Resource
    private MessageService messageService;

    @Resource
    private SmartSearchService smartSearchService;

    @Resource
    private AIService aiService;

    /**
     * 首页数据
     * @param model 视图
     * @param pageNum 分页
     * @return 渲染视图
     */
    @RequestMapping("/")
    public String toIndex(@RequestParam(required = false,defaultValue = "1") int pageNum, Model model){
        List<Blog> recommendBlog;
        List<Message> messages;
        List<Blog> hotBlogs;
        // 文章列表需要分页，不走缓存以保证 PageHelper 生效
        PageHelper.startPage(pageNum, 5);
        List<Blog> allBlog = blogService.getIndexBlog();
        if (cache.hasKey(RedisKey.RECOMMENDBLOG)){
            recommendBlog = (List<Blog>) cache.get(RedisKey.RECOMMENDBLOG);
        }else {
            recommendBlog = blogService.getAllRecommendBlog();
            cache.set(RedisKey.RECOMMENDBLOG, recommendBlog, CACHE_TTL);
        }
        if (cache.hasKey(RedisKey.MESSAGES)){
            messages = (List<Message>) cache.get(RedisKey.MESSAGES);
        }else {
            messages = messageService.findByIndexParentId();
            cache.set(RedisKey.MESSAGES, messages, CACHE_TTL);
        }
        if (cache.hasKey(RedisKey.HOTBLOGS)){
            hotBlogs = (List<Blog>) cache.get(RedisKey.HOTBLOGS);
        }else {
            hotBlogs = blogService.getHotBlog();
            cache.set(RedisKey.HOTBLOGS, hotBlogs, CACHE_TTL);
        }
        if (messages.size() >= 8){
            messages = messages.subList(0, 8);
        }
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
        if (cache.hHasKey(RedisKey.ARTICLE, String.valueOf(id))){
            blog = (Blog) cache.hGet(RedisKey.ARTICLE, String.valueOf(id));
        }else {
            blog = blogService.getDetailedBlog(id);
            cache.hSet(RedisKey.ARTICLE, String.valueOf(id), blog);
        }
        if (!cache.hHasKey(RedisKey.ARTICLE_VIEWS, String.valueOf(id))){
            cache.hSet(RedisKey.ARTICLE_VIEWS, String.valueOf(id), blog.getViews());
        }
        cache.hIncr(RedisKey.ARTICLE_VIEWS, String.valueOf(id), 1L);
        blog.setViews((Integer) cache.hGet(RedisKey.ARTICLE_VIEWS, String.valueOf(id)));
        model.addAttribute("blog", blog);
        try {
            List<Blog> relatedBlogs = smartSearchService.getRelatedBlogs(id, 4);
            model.addAttribute("relatedBlogs", relatedBlogs);
        } catch (Exception e) {
            model.addAttribute("relatedBlogs", Collections.emptyList());
        }
        return "blog";
    }

    @GetMapping("/api/search/suggestions")
    @ResponseBody
    public List<String> searchSuggestions(@RequestParam String query) {
        try {
            return smartSearchService.getSearchSuggestions(query);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    /**
     * AI 文章问答接口
     */
    @PostMapping("/api/ai/chat")
    @ResponseBody
    public CommonResult aiChat(@RequestParam Long blogId, @RequestParam String question) {
        try {
            if (question == null || question.trim().isEmpty()) {
                return CommonResult.error("请输入问题");
            }
            Blog blog;
            if (cache.hHasKey(RedisKey.ARTICLE, String.valueOf(blogId))) {
                blog = (Blog) cache.hGet(RedisKey.ARTICLE, String.valueOf(blogId));
            } else {
                blog = blogService.getDetailedBlog(blogId);
            }
            if (blog == null) {
                return CommonResult.error("文章不存在");
            }
            String answer = aiService.chatAboutArticle(blog.getTitle(), blog.getContent(), question.trim());
            return CommonResult.success(answer);
        } catch (Exception e) {
            return CommonResult.error("AI 暂时无法回答，请稍后再试");
        }
    }

    /**
     * 公开的 AI 状态接口
     */
    @GetMapping("/api/ai/status")
    @ResponseBody
    public CommonResult aiStatus() {
        java.util.Map<String, Object> status = new java.util.HashMap<>();
        status.put("enabled", aiService.isEnabled());
        return CommonResult.success(status);
    }
}
