package com.blog.util;

import com.blog.entity.Blog;
import org.springframework.stereotype.Component;

/**
 * SEO 工具类
 * 生成 Meta 标签、结构化数据等
 * @author tangredtea
 */
@Component
public class SEOUtils {
    
    private static final String SITE_NAME = "Spring Blog";
    private static final String SITE_URL = "https://your-domain.com";
    
    /**
     * 生成文章页面的 Meta Description
     */
    public String generateMetaDescription(Blog blog) {
        if (blog.getDescription() != null && !blog.getDescription().isEmpty()) {
            return truncate(blog.getDescription(), 160);
        }
        // 从内容提取
        String content = blog.getContent() != null ? 
            blog.getContent().replaceAll("<[^>]+", "") : "";
        return truncate(content, 160);
    }
    
    /**
     * 生成文章页面的 Meta Keywords
     */
    public String generateMetaKeywords(Blog blog) {
        StringBuilder keywords = new StringBuilder();
        
        // 添加标题中的关键词
        if (blog.getTitle() != null) {
            keywords.append(blog.getTitle().replaceAll("[^\\u4e00-\\u9fa5a-zA-Z0-9]", ","));
        }
        
        // 添加标签
        if (blog.getTagIds() != null) {
            keywords.append(",").append(blog.getTagIds());
        }
        
        // 添加分类
        if (blog.getType() != null && blog.getType().getName() != null) {
            keywords.append(",").append(blog.getType().getName());
        }
        
        return keywords.toString();
    }
    
    /**
     * 生成 Open Graph 标签（社交媒体分享优化）
     */
    public String generateOpenGraphTags(Blog blog, String currentUrl) {
        StringBuilder og = new StringBuilder();
        og.append("<meta property=\"og:type\" content=\"article\">\n");
        og.append("<meta property=\"og:title\" content=\"").append(escapeHtml(blog.getTitle())).append("\">\n");
        og.append("<meta property=\"og:description\" content=\"").append(escapeHtml(generateMetaDescription(blog))).append("\">\n");
        og.append("<meta property=\"og:url\" content=\"").append(SITE_URL).append(currentUrl).append("\">\n");
        og.append("<meta property=\"og:site_name\" content=\"").append(SITE_NAME).append("\">\n");
        
        if (blog.getFirstPicture() != null) {
            og.append("<meta property=\"og:image\" content=\"").append(blog.getFirstPicture()).append("\">\n");
        }
        
        if (blog.getCreateTime() != null) {
            og.append("<meta property=\"article:published_time\" content=\"").append(blog.getCreateTime()).append("\">\n");
        }
        if (blog.getUpdateTime() != null) {
            og.append("<meta property=\"article:modified_time\" content=\"").append(blog.getUpdateTime()).append("\">\n");
        }
        
        return og.toString();
    }
    
    /**
     * 生成 Twitter Card 标签
     */
    public String generateTwitterCardTags(Blog blog) {
        StringBuilder twitter = new StringBuilder();
        twitter.append("<meta name=\"twitter:card\" content=\"summary_large_image\">\n");
        twitter.append("<meta name=\"twitter:title\" content=\"").append(escapeHtml(blog.getTitle())).append("\">\n");
        twitter.append("<meta name=\"twitter:description\" content=\"").append(escapeHtml(generateMetaDescription(blog))).append("\">\n");
        
        if (blog.getFirstPicture() != null) {
            twitter.append("<meta name=\"twitter:image\" content=\"").append(blog.getFirstPicture()).append("\">\n");
        }
        
        return twitter.toString();
    }
    
    /**
     * 生成 JSON-LD 结构化数据（Schema.org）
     */
    public String generateJsonLd(Blog blog, String currentUrl) {
        StringBuilder json = new StringBuilder();
        json.append("<script type=\"application/ld+json\">\n");
        json.append("{\n");
        json.append("  \"@context\": \"https://schema.org\",\n");
        json.append("  \"@type\": \"BlogPosting\",\n");
        json.append("  \"headline\": \"").append(escapeJson(blog.getTitle())).append("\",\n");
        json.append("  \"description\": \"").append(escapeJson(generateMetaDescription(blog))).append("\",\n");
        json.append("  \"url\": \"").append(SITE_URL).append(currentUrl).append("\",\n");
        
        if (blog.getFirstPicture() != null) {
            json.append("  \"image\": [\"").append(blog.getFirstPicture()).append("\"],\n");
        }
        
        if (blog.getCreateTime() != null) {
            json.append("  \"datePublished\": \"").append(blog.getCreateTime()).append("\",\n");
        }
        if (blog.getUpdateTime() != null) {
            json.append("  \"dateModified\": \"").append(blog.getUpdateTime()).append("\",\n");
        }
        
        // 作者信息
        if (blog.getUser() != null) {
            json.append("  \"author\": {\n");
            json.append("    \"@type\": \"Person\",\n");
            json.append("    \"name\": \"").append(escapeJson(blog.getUser().getNickname())).append("\"\n");
            json.append("  },\n");
        }
        
        // 发布者信息
        json.append("  \"publisher\": {\n");
        json.append("    \"@type\": \"Organization\",\n");
        json.append("    \"name\": \"").append(SITE_NAME).append("\"\n");
        json.append("  }\n");
        
        json.append("}\n");
        json.append("</script>\n");
        
        return json.toString();
    }
    
    /**
     * 生成面包屑导航结构化数据
     */
    public String generateBreadcrumbJsonLd(String... items) {
        StringBuilder json = new StringBuilder();
        json.append("<script type=\"application/ld+json\">\n");
        json.append("{\n");
        json.append("  \"@context\": \"https://schema.org\",\n");
        json.append("  \"@type\": \"BreadcrumbList\",\n");
        json.append("  \"itemListElement\": [\n");
        
        for (int i = 0; i < items.length; i += 2) {
            if (i > 0) json.append(",\n");
            json.append("    {\n");
            json.append("      \"@type\": \"ListItem\",\n");
            json.append("      \"position\": ").append(i / 2 + 1).append(",\n");
            json.append("      \"name\": \"").append(items[i]).append("\"");
            if (i + 1 < items.length) {
                json.append(",\n      \"item\": \"").append(SITE_URL).append(items[i + 1]).append("\"");
            }
            json.append("\n    }");
        }
        
        json.append("\n  ]\n");
        json.append("}\n");
        json.append("</script>\n");
        
        return json.toString();
    }
    
    /**
     * AEO (Answer Engine Optimization) - 生成 FAQ 结构化数据
     */
    public String generateFaqJsonLd(String[][] faqs) {
        StringBuilder json = new StringBuilder();
        json.append("<script type=\"application/ld+json\">\n");
        json.append("{\n");
        json.append("  \"@context\": \"https://schema.org\",\n");
        json.append("  \"@type\": \"FAQPage\",\n");
        json.append("  \"mainEntity\": [\n");
        
        for (int i = 0; i < faqs.length; i++) {
            if (i > 0) json.append(",\n");
            json.append("    {\n");
            json.append("      \"@type\": \"Question\",\n");
            json.append("      \"name\": \"").append(escapeJson(faqs[i][0])).append("\",\n");
            json.append("      \"acceptedAnswer\": {\n");
            json.append("        \"@type\": \"Answer\",\n");
            json.append("        \"text\": \"").append(escapeJson(faqs[i][1])).append("\"\n");
            json.append("      }\n");
            json.append("    }");
        }
        
        json.append("\n  ]\n");
        json.append("}\n");
        json.append("</script>\n");
        
        return json.toString();
    }
    
    // 辅助方法
    private String truncate(String str, int maxLength) {
        if (str == null || str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength - 3) + "...";
    }
    
    private String escapeHtml(String str) {
        if (str == null) return "";
        return str.replace("&", "&amp;")
                  .replace("<", "&lt;")
                  .replace(">", "&gt;")
                  .replace("\"", "&quot;");
    }
    
    private String escapeJson(String str) {
        if (str == null) return "";
        return str.replace("\\", "\\\\")
                  .replace("\"", "\\\"")
                  .replace("\n", "\\n")
                  .replace("\r", "\\r")
                  .replace("\t", "\\t");
    }
}
