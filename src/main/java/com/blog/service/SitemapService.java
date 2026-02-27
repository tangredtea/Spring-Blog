package com.blog.service;

import com.blog.entity.Blog;
import com.blog.dao.BlogDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 站点地图服务
 * 自动生成 sitemap.xml 用于 SEO
 * @author tangredtea
 */
@Slf4j
@Service
public class SitemapService {

    @Resource
    private BlogDao blogDao;
    
    private static final String SITE_URL = "https://your-domain.com";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    /**
     * 生成 XML 格式的站点地图
     */
    public String generateSitemap() {
        StringBuilder sitemap = new StringBuilder();
        sitemap.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sitemap.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n");
        
        // 首页
        addUrl(sitemap, "/", "1.0", "daily");
        
        // 分类页
        addUrl(sitemap, "/types", "0.8", "weekly");
        
        // 标签页
        addUrl(sitemap, "/tags", "0.8", "weekly");
        
        // 关于页
        addUrl(sitemap, "/about", "0.5", "monthly");
        
        // 留言页
        addUrl(sitemap, "/message", "0.6", "weekly");
        
        // 所有已发布的文章
        List<Blog> blogs = blogDao.getAllPublishedBlogs();
        for (Blog blog : blogs) {
            String lastmod = blog.getUpdateTime() != null ? 
                DATE_FORMAT.format(blog.getUpdateTime().toInstant()) :
                DATE_FORMAT.format(LocalDateTime.now());
            addUrl(sitemap, "/blog/" + blog.getId(), "0.9", "weekly", lastmod);
        }
        
        sitemap.append("</urlset>");
        return sitemap.toString();
    }
    
    private void addUrl(StringBuilder sb, String loc, String priority, String changefreq) {
        addUrl(sb, loc, priority, changefreq, DATE_FORMAT.format(LocalDateTime.now()));
    }
    
    private void addUrl(StringBuilder sb, String loc, String priority, String changefreq, String lastmod) {
        sb.append("  <url>\n");
        sb.append("    <loc>").append(SITE_URL).append(loc).append("</loc>\n");
        sb.append("    <lastmod>").append(lastmod).append("</lastmod>\n");
        sb.append("    <changefreq>").append(changefreq).append("</changefreq>\n");
        sb.append("    <priority>").append(priority).append("</priority>\n");
        sb.append("  </url>\n");
    }
}
