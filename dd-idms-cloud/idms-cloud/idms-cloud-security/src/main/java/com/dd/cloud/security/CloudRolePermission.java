package com.dd.cloud.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CloudRolePermission {
    public boolean hasRole (Authentication authentication, Object object,
                            Collection<ConfigAttribute> attributes) {
        if (authentication == null) {
            return false;
        }
        List<String> authorities = extractAuthorities(authentication);
        for (ConfigAttribute attribute : attributes) {
            String   attr=getAttribute(attribute);
            for (String authority : authorities) {
                if (attr.contains(authority)) {
                    return true;
                }
            }
        }
        return false;
    }

    List<String> extractAuthorities(Authentication authentication) {
        List<String> stringList=new ArrayList<>();
        authentication.getAuthorities().stream().forEach(n->{
            if(n.getAuthority().contains("ROLE_")){
                stringList.add(n.getAuthority());
            }
        });
        return stringList;
    }
    public String getAttribute(ConfigAttribute attribute) {
        if(attribute!=null){
            return attribute.toString();
        }
        else {
            return "";
        }
    }
}
