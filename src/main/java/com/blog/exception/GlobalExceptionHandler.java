package com.blog.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 全局异常处理器
 * @author Ryan
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有异常
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        log.error("系统异常", e);
        model.addAttribute("errorMsg", "系统繁忙，请稍后重试");
        return "error/error";
    }

    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public String handleBusinessException(BusinessException e, RedirectAttributes attributes) {
        log.warn("业务异常: {}", e.getMessage());
        attributes.addFlashAttribute("msg", e.getMessage());
        return "redirect:" + e.getRedirectUrl();
    }
}
