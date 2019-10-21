package com.github.sdcxy.exception;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    Object doExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e);
}
