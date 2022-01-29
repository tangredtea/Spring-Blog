package com.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 把博客和标签关系存到数据库中使用的类
 * @author Ryan
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogAndTag implements Serializable {

    private Long tagId;

    private Long blogId;
}
