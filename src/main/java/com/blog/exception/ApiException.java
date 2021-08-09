package com.blog.exception;

import com.blog.entity.CommonResult;

/**
 * @author Ryan
 */
public class ApiException extends RuntimeException {
    public ApiException() { }

    public ApiException(String message) {
        super(message);
    }

}
