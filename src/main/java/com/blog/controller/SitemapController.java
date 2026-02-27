package com.blog.controller;

import com.blog.service.SitemapService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

/**
 * 站点地图控制器
 * 提供 sitemap.xml 用于搜索引擎收录
 * @author Ryan
 */
@Controller
public class SitemapController {

    @Resource
    private SitemapService sitemapService;
    
    /**
     * 生成站点地图
     * 访问地址: /sitemap.xml
     */
    @GetMapping(value = "/sitemap.xml", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String sitemap() {
        return sitemapService.generateSitemap();
    }
}
