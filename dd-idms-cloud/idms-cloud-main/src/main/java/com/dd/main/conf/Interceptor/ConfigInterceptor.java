package com.dd.main.conf.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class ConfigInterceptor extends WebContentInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html" + ";charset=UTF-8");
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        return true;
    }
}
