package com.blog.entity;

import lombok.Data;

/**
 * 公共逻辑返回类
 * @author Ryan
 */
@Data
public class CommonResult<T> {
    private Integer code;
    private String msg;
    private T data;

    public CommonResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> CommonResult<T> success(T t) {
        return new CommonResult<>(200, "操作成功", t);
    }

    public static <T> CommonResult<T> error(T t) {
        return new CommonResult<>(500, "操作失败", t);
    }

}

