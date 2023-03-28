package com.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ryan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebhookMessage {
    /**
     * the text to send
     */
    private String text;

    public Map<String, Object> toJsonString() {
        Map<String, Object> items = new HashMap<>();
        items.put("msgtype", "text");
        Map<String, Object> textContent = new HashMap<>();
        textContent.put("content", text);
        items.put("text", textContent);
        return items;
    }
}
