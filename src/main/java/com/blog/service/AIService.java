package com.blog.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * AI 服务
 * 集成大模型 API 提供智能功能
 * @author tangredtea
 */
@Slf4j
@Service
public class AIService {

    @Value("${ai.api.key:}")
    private String apiKey;
    
    @Value("${ai.api.url:https://api.openai.com/v1/chat/completions}")
    private String apiUrl;
    
    @Value("${ai.model:gpt-3.5-turbo}")
    private String model;
    
    private RestTemplate restTemplate;
    private boolean aiEnabled = false;
    
    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate();
        aiEnabled = !apiKey.isEmpty();
        if (aiEnabled) {
            log.info("AI service initialized with model: {}", model);
        } else {
            log.warn("AI service disabled: API key not configured");
        }
    }
    
    /**
     * 生成文章摘要
     * @param content 文章内容
     * @return 生成的摘要
     */
    public String generateSummary(String content) {
        if (!aiEnabled) {
            return generateLocalSummary(content);
        }
        
        try {
            String prompt = "请为以下文章生成一段简洁的摘要（100字以内）：\n\n" + 
                           content.substring(0, Math.min(content.length(), 2000));
            
            return callAI(prompt);
        } catch (Exception e) {
            log.error("AI summary generation failed", e);
            return generateLocalSummary(content);
        }
    }
    
    /**
     * 生成文章标签建议
     * @param title 文章标题
     * @param content 文章内容
     * @return 标签建议数组
     */
    public String[] suggestTags(String title, String content) {
        if (!aiEnabled) {
            return new String[0];
        }
        
        try {
            String prompt = String.format(
                "请为以下文章推荐3-5个合适的标签，用逗号分隔：\n标题：%s\n内容：%s",
                title,
                content.substring(0, Math.min(content.length(), 1000))
            );
            
            String response = callAI(prompt);
            return response.split("，|,\\s*");
        } catch (Exception e) {
            log.error("AI tag suggestion failed", e);
            return new String[0];
        }
    }
    
    /**
     * 智能回复评论
     * @param comment 评论内容
     * @param articleTitle 文章标题
     * @return 回复内容
     */
    public String generateReply(String comment, String articleTitle) {
        if (!aiEnabled) {
            return "感谢您的评论！";
        }
        
        try {
            String prompt = String.format(
                "作为博主，请礼貌地回复以下评论（50字以内）：\n文章：《%s》\n评论：%s",
                articleTitle,
                comment
            );
            
            return callAI(prompt);
        } catch (Exception e) {
            log.error("AI reply generation failed", e);
            return "感谢您的评论！";
        }
    }
    
    /**
     * 文章质量评分
     * @param title 标题
     * @param content 内容
     * @return 评分和建议
     */
    public ArticleScore scoreArticle(String title, String content) {
        if (!aiEnabled) {
            return new ArticleScore(70, "AI 服务未启用，使用默认评分");
        }
        
        try {
            String prompt = String.format(
                "请对以下博客文章进行评分（0-100分），并给出简要建议：\n标题：%s\n内容：%s\n\n格式：分数|建议",
                title,
                content.substring(0, Math.min(content.length(), 1500))
            );
            
            String response = callAI(prompt);
            String[] parts = response.split("\\|", 2);
            
            int score = 70;
            try {
                score = Integer.parseInt(parts[0].replaceAll("\\D", ""));
            } catch (NumberFormatException ignored) {}
            
            String suggestion = parts.length > 1 ? parts[1].trim() : "继续加油！";
            
            return new ArticleScore(score, suggestion);
        } catch (Exception e) {
            log.error("AI article scoring failed", e);
            return new ArticleScore(70, "评分服务暂时不可用");
        }
    }
    
    /**
     * 调用 AI API
     */
    private String callAI(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        
        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);
        
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", model);
        requestBody.put("messages", new Object[]{message});
        requestBody.put("temperature", 0.7);
        requestBody.put("max_tokens", 500);
        
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        
        @SuppressWarnings("unchecked")
        Map<String, Object> response = restTemplate.postForObject(apiUrl, request, Map.class);
        
        if (response != null && response.containsKey("choices")) {
            @SuppressWarnings("unchecked")
            java.util.List<Map<String, Object>> choices = (java.util.List<Map<String, Object>>) response.get("choices");
            if (!choices.isEmpty()) {
                @SuppressWarnings("unchecked")
                Map<String, Object> choice = choices.get(0);
                @SuppressWarnings("unchecked")
                Map<String, String> message_ = (Map<String, String>) choice.get("message");
                return message_.get("content").trim();
            }
        }
        
        throw new RuntimeException("Invalid AI response");
    }
    
    /**
     * 本地摘要生成（备用方案）
     */
    private String generateLocalSummary(String content) {
        // 去除 HTML 标签
        String text = content.replaceAll("<[^>]+", "");
        // 取前 150 字符
        if (text.length() > 150) {
            return text.substring(0, 150) + "...";
        }
        return text;
    }
    
    /**
     * 检查 AI 是否可用
     */
    public boolean isEnabled() {
        return aiEnabled;
    }
    
    /**
     * 文章评分结果
     */
    public static class ArticleScore {
        private final int score;
        private final String suggestion;
        
        public ArticleScore(int score, String suggestion) {
            this.score = score;
            this.suggestion = suggestion;
        }
        
        public int getScore() { return score; }
        public String getSuggestion() { return suggestion; }
    }
}
