package com.dd.cloud.security;

import com.dd.cloud.core.common.json.JsonResult;
import com.dd.cloud.core.common.utilities.RedisCacheUtilities;
import com.dd.cloud.core.common.utilities.RedisUtil;
import com.dd.cloud.core.common.utilities.ToolUtilities;
import com.dd.cloud.security.model.entity.SecurityUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.dd.cloud.core.common.constant.CacheKey.USER_INFO_PRI_KEY;


/**
 * 用户登陆成功后，用户信息写入缓存
 */
@Slf4j
@Component
public class CloudLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private RedisCacheUtilities redisCacheUtilities;

    @Override

    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException {
        boolean isAjaxRequest = ToolUtilities.isAjaxRequest(request);
        if (isAjaxRequest) {
            clearAuthenticationAttributes(request);
            SecurityUserInfo userInfo = (SecurityUserInfo) authentication.getPrincipal();
            String key = USER_INFO_PRI_KEY + userInfo.getAccountName();
            if (!redisCacheUtilities.hasKey(key)) {
                redisCacheUtilities.set(key, userInfo);
            }
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JsonResult.create().message("登陆成功").success(true).toString());
        }
    }

}
