package com.blog.util;

import com.blog.entity.Blog;
import com.blog.entity.Type;
import com.blog.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SEO 工具类单元测试
 * @author Ryan
 */
class SEOUtilsTest {

    private SEOUtils seoUtils;
    private Blog blog;

    @BeforeEach
    void setUp() {
        seoUtils = new SEOUtils();
        
        // 创建测试博客对象
        blog = new Blog();
        blog.setId(1L);
        blog.setTitle("Spring Boot 入门教程");
        blog.setDescription("本文介绍 Spring Boot 框架的基本使用方法");
        blog.setContent("<p>Spring Boot 是一个简化 Spring 应用开发的框架。</p><p>它提供了自动配置功能。</p>");
        blog.setFirstPicture("https://example.com/image.jpg");
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setTagIds("spring,boot,java");
        
        Type type = new Type();
        type.setName("后端开发");
        blog.setType(type);
        
        User user = new User();
        user.setNickname("博主小明");
        blog.setUser(user);
    }

    @Test
    void testGenerateMetaDescription_WithDescription() {
        // When
        String description = seoUtils.generateMetaDescription(blog);

        // Then
        assertNotNull(description);
        assertTrue(description.contains("Spring Boot"));
        assertTrue(description.length() <= 160);
    }

    @Test
    void testGenerateMetaDescription_WithoutDescription() {
        // Given
        blog.setDescription(null);

        // When
        String description = seoUtils.generateMetaDescription(blog);

        // Then
        assertNotNull(description);
        assertFalse(description.contains("<p>")); // HTML 标签应该被去除
    }

    @Test
    void testGenerateMetaKeywords() {
        // When
        String keywords = seoUtils.generateMetaKeywords(blog);

        // Then
        assertNotNull(keywords);
        assertTrue(keywords.contains("Spring Boot"));
        assertTrue(keywords.contains("spring"));
        assertTrue(keywords.contains("后端开发"));
    }

    @Test
    void testGenerateOpenGraphTags() {
        // When
        String ogTags = seoUtils.generateOpenGraphTags(blog, "/blog/1");

        // Then
        assertNotNull(ogTags);
        assertTrue(ogTags.contains("og:type"));
        assertTrue(ogTags.contains("og:title"));
        assertTrue(ogTags.contains("og:description"));
        assertTrue(ogTags.contains("og:url"));
        assertTrue(ogTags.contains("og:image"));
        assertTrue(ogTags.contains("article:published_time"));
    }

    @Test
    void testGenerateTwitterCardTags() {
        // When
        String twitterTags = seoUtils.generateTwitterCardTags(blog);

        // Then
        assertNotNull(twitterTags);
        assertTrue(twitterTags.contains("twitter:card"));
        assertTrue(twitterTags.contains("twitter:title"));
        assertTrue(twitterTags.contains("twitter:description"));
        assertTrue(twitterTags.contains("twitter:image"));
    }

    @Test
    void testGenerateJsonLd() {
        // When
        String jsonLd = seoUtils.generateJsonLd(blog, "/blog/1");

        // Then
        assertNotNull(jsonLd);
        assertTrue(jsonLd.contains("@context"));
        assertTrue(jsonLd.contains("BlogPosting"));
        assertTrue(jsonLd.contains("headline"));
        assertTrue(jsonLd.contains("author"));
        assertTrue(jsonLd.contains("publisher"));
    }

    @Test
    void testGenerateBreadcrumbJsonLd() {
        // When
        String breadcrumb = seoUtils.generateBreadcrumbJsonLd(
            "首页", "/",
            "文章列表", "/blogs",
            "当前文章", "/blog/1"
        );

        // Then
        assertNotNull(breadcrumb);
        assertTrue(breadcrumb.contains("BreadcrumbList"));
        assertTrue(breadcrumb.contains("ListItem"));
        assertTrue(breadcrumb.contains("首页"));
        assertTrue(breadcrumb.contains("文章列表"));
    }

    @Test
    void testGenerateFaqJsonLd() {
        // Given
        String[][] faqs = {
            {"什么是 Spring Boot？", "Spring Boot 是一个简化 Spring 开发的框架。"},
            {"如何学习 Spring Boot？", "可以通过官方文档和实战项目学习。"}
        };

        // When
        String faqJson = seoUtils.generateFaqJsonLd(faqs);

        // Then
        assertNotNull(faqJson);
        assertTrue(faqJson.contains("FAQPage"));
        assertTrue(faqJson.contains("Question"));
        assertTrue(faqJson.contains("Answer"));
    }

    @Test
    void testHtmlEscape() {
        // Given
        blog.setTitle("<script>alert('xss')</script>");

        // When
        String ogTags = seoUtils.generateOpenGraphTags(blog, "/blog/1");

        // Then
        assertFalse(ogTags.contains("<script>"));
        assertTrue(ogTags.contains("&lt;script&gt;"));
    }
}
