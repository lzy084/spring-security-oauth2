package com.dd.cloud;

import com.dd.cloud.core.common.json.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResourceEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        if(authException instanceof AuthenticationCredentialsNotFoundException){
            String token= request.getHeader("Authorization");
            if(StringUtils.isNotBlank(token)){
                token=token.replace("Bearer ","");
                response.getWriter().write(JsonResult.create().code("401").message(token+":无效").success(false).toString());
            }
            else{
                response.getWriter().write(JsonResult.create().code("403").message("请认证后访问").success(false).toString());
            }
            return;
        }
        response.getWriter().write(JsonResult.create().code("403").message("请认证后访问").success(false).toString());
        return;
    }
}
