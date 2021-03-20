package com.dd.cloud.security;

import com.dd.cloud.core.common.json.JsonResult;
import com.dd.cloud.core.common.utilities.ToolUtilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CloudLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        boolean isAjaxRequest = ToolUtilities.isAjaxRequest(request);
        String errorMessage;
        if (isAjaxRequest) {

            if (exception instanceof UsernameNotFoundException) {
                errorMessage = "User does not exist";
            } else if (exception instanceof DisabledException) {
                errorMessage = "The account is locked.";
            } else if (exception instanceof BadCredentialsException) {
                errorMessage = exception.getMessage();
            } else {
                errorMessage = "login failed,please contract admin";
            }
            log.info("login failure reason:{}", errorMessage);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JsonResult.create().message(errorMessage).success(false).toString());
            return;
        }
    }
}
