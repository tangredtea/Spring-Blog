package com.blog.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AI 服务单元测试
 * @author Ryan
 */
@SpringBootTest
@TestPropertySource(properties = {
    "ai.api.key=test-key",
    "ai.model=gpt-3.5-turbo"
})
class AIServiceTest {

    @Autowired
    private AIService aiService;

    @BeforeEach
    void setUp() {
        // 测试前准备
    }

    @Test
    void testGenerateSummary_WithLongContent() {
        // Given
        String content = "这是一篇关于Spring Boot的博客文章。" +
                "Spring Boot是一个简化Spring应用开发的框架。" +
                "它提供了自动配置、起步依赖等特性，让开发者可以快速搭建项目。" +
                "本文将详细介绍Spring Boot的核心特性和使用方法。";

        // When
        String summary = aiService.generateSummary(content);

        // Then
        assertNotNull(summary);
        assertTrue(summary.length() <= 200);
    }

    @Test
    void testGenerateSummary_WithShortContent() {
        // Given
        String content = "短文测试";

        // When
        String summary = aiService.generateSummary(content);

        // Then
        assertNotNull(summary);
        assertEquals("短文测试", summary);
    }

    @Test
    void testSuggestTags() {
        // Given
        String title = "Spring Boot 入门教程";
        String content = "本文介绍如何使用 Spring Boot 快速开发 Web 应用，包括自动配置、起步依赖等核心概念。";

        // When - 如果 AI 未启用，返回空数组
        String[] tags = aiService.suggestTags(title, content);

        // Then
        assertNotNull(tags);
        // 如果 AI 未配置，应该返回空数组而不是 null
    }

    @Test
    void testScoreArticle() {
        // Given
        String title = "测试文章标题";
        String content = "这是一篇测试文章的内容，用于测试评分功能。";

        // When
        AIService.ArticleScore score = aiService.scoreArticle(title, content);

        // Then
        assertNotNull(score);
        assertTrue(score.getScore() >= 0 && score.getScore() <= 100);
        assertNotNull(score.getSuggestion());
    }

    @Test
    void testIsEnabled() {
        // When & Then
        // 由于配置了 test-key，应该返回 true
        assertTrue(aiService.isEnabled());
    }

    @Test
    void testLocalSummaryGeneration() {
        // Given - HTML 内容
        String htmlContent = "<p>这是第一段</p><p>这是第二段，包含更多文字内容用于测试摘要生成功能。</p>";

        // When
        String summary = aiService.generateSummary(htmlContent);

        // Then
        assertNotNull(summary);
        assertFalse(summary.contains("<p>")); // HTML 标签应该被去除
    }
}
