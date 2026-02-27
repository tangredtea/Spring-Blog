package com.blog.service;

import com.blog.dao.BlogDao;
import com.blog.entity.Blog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 智能搜索服务
 * 提供语义化搜索和相关推荐
 * @author Ryan
 */
@Slf4j
@Service
public class SmartSearchService {

    @Resource
    private BlogDao blogDao;
    
    /**
     * 提取关键词
     * @param text 文本内容
     * @return 关键词列表
     */
    public List<String> extractKeywords(String text) {
        // 简单的中文分词实现
        // 实际项目中可以使用 HanLP、jieba 等分词库
        Set<String> stopWords = new HashSet<>(Arrays.asList(
            "的", "了", "在", "是", "我", "有", "和", "就", "不", "人", "都", "一", "一个", "上", "也",
            "很", "到", "说", "要", "去", "你", "会", "着", "没有", "看", "好", "自己", "这"
        ));
        
        // 去除 HTML 标签
        String cleanText = text.replaceAll("<[^>]+", "");
        
        // 按非中文字符分割
        String[] words = cleanText.split("[^\\u4e00-\\u9fa5a-zA-Z0-9]+");
        
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            word = word.toLowerCase().trim();
            if (word.length() >= 2 && !stopWords.contains(word)) {
                wordFreq.merge(word, 1, Integer::sum);
            }
        }
        
        // 按频率排序，取前 10
        return wordFreq.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
    
    /**
     * 计算文章相似度
     * @param blog1 文章1
     * @param blog2 文章2
     * @return 相似度分数 (0-1)
     */
    public double calculateSimilarity(Blog blog1, Blog blog2) {
        // 基于标签和分类计算相似度
        double score = 0.0;
        
        // 同分类 +0.3
        if (blog1.getTypeId() != null && blog1.getTypeId().equals(blog2.getTypeId())) {
            score += 0.3;
        }
        
        // 标签重叠
        if (blog1.getTagIds() != null && blog2.getTagIds() != null) {
            Set<String> tags1 = new HashSet<>(Arrays.asList(blog1.getTagIds().split(",")));
            Set<String> tags2 = new HashSet<>(Arrays.asList(blog2.getTagIds().split(",")));
            
            Set<String> intersection = new HashSet<>(tags1);
            intersection.retainAll(tags2);
            
            if (!tags1.isEmpty() || !tags2.isEmpty()) {
                score += 0.7 * ((double) intersection.size() / 
                    Math.max(tags1.size(), tags2.size()));
            }
        }
        
        return score;
    }
    
    /**
     * 获取相关文章推荐
     * @param blogId 当前文章ID
     * @param limit 返回数量
     * @return 相关文章列表
     */
    public List<Blog> getRelatedBlogs(Long blogId, int limit) {
        Blog currentBlog = blogDao.getBlog(blogId);
        if (currentBlog == null) {
            return Collections.emptyList();
        }
        
        List<Blog> allBlogs = blogDao.getAllPublishedBlogs();
        
        return allBlogs.stream()
                .filter(b -> !b.getId().equals(blogId))
                .map(b -> new AbstractMap.SimpleEntry<>(b, calculateSimilarity(currentBlog, b)))
                .filter(e -> e.getValue() > 0.1)  // 过滤低相似度
                .sorted(Map.Entry.<Blog, Double>comparingByValue().reversed())
                .limit(limit)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
    
    /**
     * 智能搜索建议
     * @param query 搜索词
     * @return 建议列表
     */
    public List<String> getSearchSuggestions(String query) {
        if (query == null || query.trim().isEmpty()) {
            return Collections.emptyList();
        }
        
        List<Blog> blogs = blogDao.getAllPublishedBlogs();
        String lowerQuery = query.toLowerCase();
        
        return blogs.stream()
                .filter(b -> b.getTitle() != null && 
                       b.getTitle().toLowerCase().contains(lowerQuery))
                .map(Blog::getTitle)
                .limit(5)
                .collect(Collectors.toList());
    }
}
