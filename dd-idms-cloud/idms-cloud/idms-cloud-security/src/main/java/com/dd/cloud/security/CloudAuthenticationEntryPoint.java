package com.dd.cloud.security;


import com.dd.cloud.core.common.json.JsonResult;
import com.dd.cloud.core.common.utilities.ToolUtilities;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CloudAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private String defaultUrl = "/login.html";
    DefaultRedirectStrategy defaultRedirectStrategy=new DefaultRedirectStrategy();
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        boolean isAjaxRequest = ToolUtilities.isAjaxRequest(request);
        if (isAjaxRequest) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JsonResult.create().code("403").message("请登录访问").success(false).toString());
            return;
        }
        defaultRedirectStrategy.sendRedirect(request, response, defaultUrl);
    }
}
