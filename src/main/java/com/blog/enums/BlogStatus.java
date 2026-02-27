package com.blog.enums;

import lombok.Getter;

/**
 * 博客文章状态枚举
 * @author tangredtea
 */
@Getter
public enum BlogStatus {
    
    DRAFT(0, "草稿"),
    PUBLISHED(1, "已发布"),
    DELETED(2, "已删除（回收站）");
    
    private final int code;
    private final String desc;
    
    BlogStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
