package com.blog.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 日志类，用于封装请求信息
 * @author Ryan
 */
@Data
@AllArgsConstructor
public class RequestLog{
    private String url;
    private String ip;
    private String classMethod;
    private Object[] args;
}