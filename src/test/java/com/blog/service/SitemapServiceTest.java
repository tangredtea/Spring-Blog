package com.blog.service;

import com.blog.dao.BlogDao;
import com.blog.entity.Blog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * 站点地图服务单元测试
 * @author Ryan
 */
class SitemapServiceTest {

    @Mock
    private BlogDao blogDao;

    @InjectMocks
    private SitemapService sitemapService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateSitemap() {
        // Given
        Blog blog1 = new Blog();
        blog1.setId(1L);
        blog1.setTitle("文章1");
        blog1.setUpdateTime(new Date());

        Blog blog2 = new Blog();
        blog2.setId(2L);
        blog2.setTitle("文章2");
        blog2.setUpdateTime(new Date());

        List<Blog> blogs = Arrays.asList(blog1, blog2);
        when(blogDao.getAllPublishedBlogs()).thenReturn(blogs);

        // When
        String sitemap = sitemapService.generateSitemap();

        // Then
        assertNotNull(sitemap);
        assertTrue(sitemap.contains("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
        assertTrue(sitemap.contains("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">"));
        assertTrue(sitemap.contains("<loc>"));
        assertTrue(sitemap.contains("<priority>"));
        assertTrue(sitemap.contains("<changefreq>"));
        assertTrue(sitemap.contains("</urlset>"));
        
        // 验证包含首页和文章链接
        assertTrue(sitemap.contains("/blog/1"));
        assertTrue(sitemap.contains("/blog/2"));
        assertTrue(sitemap.contains("/types"));
        assertTrue(sitemap.contains("/tags"));
        
        verify(blogDao, times(1)).getAllPublishedBlogs();
    }

    @Test
    void testGenerateSitemap_WithEmptyBlogs() {
        // Given
        when(blogDao.getAllPublishedBlogs()).thenReturn(Arrays.asList());

        // When
        String sitemap = sitemapService.generateSitemap();

        // Then
        assertNotNull(sitemap);
        assertTrue(sitemap.contains("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
        // 即使没有文章，也应该包含基本页面
        assertTrue(sitemap.contains("/types"));
        assertTrue(sitemap.contains("/tags"));
    }

    @Test
    void testGenerateSitemap_ContainsPriority() {
        // Given
        Blog blog = new Blog();
        blog.setId(1L);
        blog.setUpdateTime(new Date());
        when(blogDao.getAllPublishedBlogs()).thenReturn(Arrays.asList(blog));

        // When
        String sitemap = sitemapService.generateSitemap();

        // Then
        assertTrue(sitemap.contains("<priority>1.0</priority>")); // 首页优先级最高
        assertTrue(sitemap.contains("<priority>0.9</priority>")); // 文章优先级较高
    }

    @Test
    void testGenerateSitemap_ContainsChangeFreq() {
        // Given
        when(blogDao.getAllPublishedBlogs()).thenReturn(Arrays.asList());

        // When
        String sitemap = sitemapService.generateSitemap();

        // Then
        assertTrue(sitemap.contains("<changefreq>daily</changefreq>"));
        assertTrue(sitemap.contains("<changefreq>weekly</changefreq>"));
        assertTrue(sitemap.contains("<changefreq>monthly</changefreq>"));
    }
}
