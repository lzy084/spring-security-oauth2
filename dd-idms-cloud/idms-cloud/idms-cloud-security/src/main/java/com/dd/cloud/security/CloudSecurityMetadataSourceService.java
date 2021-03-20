package com.dd.cloud.security;


import com.dd.cloud.security.model.entity.SecurityResource;
import com.dd.cloud.security.model.service.SecurityResourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CloudSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
    @Autowired
    SecurityResourceService resourceService;
    private Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<>();
    private void loadResourceDefine() {
        List<SecurityResource> menuResourceList = resourceService.findAllResource();
        for (SecurityResource resource : menuResourceList) {
            Collection<ConfigAttribute> configAttributes = new ArrayList<>();
            List<String> permissionList = Stream.of(resource).map(n -> n.getPermission()).collect(Collectors.toList());
            for (String permissionCode : permissionList) {
                ConfigAttribute attribute = new SecurityConfig(permissionCode);
                configAttributes.add(attribute);
            }
            resourceMap.put(resource.getAction(), configAttributes);
        }
    }

    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (resourceMap.size() == 0) {
            loadResourceDefine();
        }
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for (Iterator<String> iter = resourceMap.keySet().iterator(); iter.hasNext(); ) {
            String iterNext = iter.next();
            if (StringUtils.isNotBlank(iterNext)) {
                resUrl = iterNext;
                matcher = new AntPathRequestMatcher(resUrl);
                if (matcher.matches(request)) {
                    return resourceMap.get(resUrl);
                }
            }

        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        if (resourceMap == null)
            return null;
        else {
            List<ConfigAttribute> configAttributes = new ArrayList<>();
            for (Map.Entry<String, Collection<ConfigAttribute>> ignored : resourceMap.entrySet()) {
                configAttributes.addAll(ignored.getValue());
            }
            return configAttributes;
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
