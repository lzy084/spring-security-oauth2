package com.dd.cloud.security;

import com.dd.cloud.security.model.entity.SecurityUserInfo;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CloudPermissionEvaluator implements PermissionEvaluator {
    /**
     * spring-security 表达式 权限认证
     * @param authentication 当前主体用户
     * @param targetDomainObject 当前请求url 或 action
     * @param permission 请求的action 的权限 例如：@PreAuthorize("hasPermission('/admin','r')") 中r
     * @return 认证成功返回true，认证失败返回false
     */

    /**
     * spring-security 表达式 权限认证
     * @param authentication 当前主体用户
     * @param targetDomainObject 当前请求url 或 action
     * @param permission 请求的action 的权限 例如：@PreAuthorize("hasPermission('/admin','r')") 中r
     * @return 认证成功返回true，认证失败返回false
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        SecurityUserInfo userDetails=(SecurityUserInfo)authentication.getPrincipal();
        Set<GrantedAuthority> set= userDetails.getAuthorities();
        while (set.iterator().hasNext()){
            GrantedAuthority grantedAuthority=set.iterator().next();
            String grant= grantedAuthority.getAuthority();
            List<String> userPermissionList=userDetails.getAuthorities().stream().map(n->n.getAuthority()).collect(Collectors.toList());
            for(String userPermission:userPermissionList){
                if(permission.equals(grant)&& targetDomainObject.equals(userPermission)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
