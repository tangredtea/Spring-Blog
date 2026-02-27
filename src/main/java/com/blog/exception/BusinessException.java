package com.blog.exception;

import lombok.Getter;

/**
 * 业务异常
 * @author Ryan
 */
@Getter
public class BusinessException extends RuntimeException {

    private final String redirectUrl;

    public BusinessException(String message) {
        super(message);
        this.redirectUrl = "/admin";
    }

    public BusinessException(String message, String redirectUrl) {
        super(message);
        this.redirectUrl = redirectUrl;
    }
}
