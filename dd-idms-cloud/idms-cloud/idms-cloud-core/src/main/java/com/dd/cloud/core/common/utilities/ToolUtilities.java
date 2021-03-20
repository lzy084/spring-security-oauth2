package com.dd.cloud.core.common.utilities;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class ToolUtilities {
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isLogin(HttpServletRequest request) {
        if (request.getRequestURI().equals("/login")) {
            return true;
        } else {
            return false;
        }
    }

    public static String SimpleKey(Object... elements) {
        String simpleKey="";
        int length=0;
        for(int i=0;i<elements.length;i++){
            if(i==elements.length-1){
                simpleKey+=elements[i];
            }
            else{
                simpleKey+=elements[i]+":";
            }

        }
        return simpleKey;
    }
    public static <T> T ObjectConvert(Object origin,Class<T> t)  {
       String destStr= JSONObject.toJSONString(origin);
       return JSONObject.parseObject(destStr,t);
    }
}
