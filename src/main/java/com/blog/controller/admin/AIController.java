package com.blog.controller.admin;

import com.blog.service.AIService;
import com.blog.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * AI 功能控制器
 * @author tangredtea
 */
@Slf4j
@Controller
@RequestMapping("/admin/ai")
public class AIController {

    @Resource
    private AIService aiService;

    /**
     * AI 助手页面
     */
    @GetMapping("/assistant")
    public String assistant() {
        return "admin/ai-assistant";
    }

    /**
     * 生成文章摘要
     */
    @PostMapping("/summary")
    @ResponseBody
    public CommonResult generateSummary(@RequestParam String content) {
        try {
            String summary = aiService.generateSummary(content);
            return CommonResult.success(summary);
        } catch (Exception e) {
            log.error("Generate summary failed", e);
            return CommonResult.error("生成摘要失败：" + e.getMessage());
        }
    }
    
    /**
     * 推荐标签
     */
    @PostMapping("/suggest-tags")
    @ResponseBody
    public CommonResult suggestTags(@RequestParam String title, 
                                     @RequestParam String content) {
        try {
            String[] tags = aiService.suggestTags(title, content);
            return CommonResult.success(tags);
        } catch (Exception e) {
            log.error("Suggest tags failed", e);
            return CommonResult.error("推荐标签失败：" + e.getMessage());
        }
    }
    
    /**
     * 文章质量评分
     */
    @PostMapping("/score")
    @ResponseBody
    public CommonResult scoreArticle(@RequestParam String title,
                                      @RequestParam String content) {
        try {
            AIService.ArticleScore score = aiService.scoreArticle(title, content);
            Map<String, Object> result = new HashMap<>();
            result.put("score", score.getScore());
            result.put("suggestion", score.getSuggestion());
            result.put("level", getScoreLevel(score.getScore()));
            return CommonResult.success(result);
        } catch (Exception e) {
            log.error("Score article failed", e);
            return CommonResult.error("评分失败：" + e.getMessage());
        }
    }
    
    /**
     * 检查 AI 状态
     */
    @GetMapping("/status")
    @ResponseBody
    public CommonResult checkStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("enabled", aiService.isEnabled());
        status.put("message", aiService.isEnabled() ? "AI 服务正常运行" : "AI 服务未配置");
        return CommonResult.success(status);
    }
    
    private String getScoreLevel(int score) {
        if (score >= 90) return "优秀";
        if (score >= 80) return "良好";
        if (score >= 70) return "合格";
        if (score >= 60) return "待改进";
        return "需优化";
    }
}
