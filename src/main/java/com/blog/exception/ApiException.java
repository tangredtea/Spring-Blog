package com.blog.exception;

/**
 * @author Ryan
 */
public class ApiException extends RuntimeException {
    public ApiException() { }

    public ApiException(String message) {
        super(message);
    }

}
