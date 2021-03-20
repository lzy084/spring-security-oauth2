package com.dd.cloud.security;

import com.dd.cloud.core.common.utilities.RedisCacheUtilities;
import com.dd.cloud.core.common.utilities.RedisUtil;
import com.dd.cloud.security.exception.CloudAccessDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CloudAccessDecisionManager implements AccessDecisionManager {
    @Autowired
    private CloudRolePermission cloudRolePermission;
    @Autowired
    RedisCacheUtilities redisCacheUtilities;

    public final  String rolePrefix="ROLE_";

    /**
     * decide 方法是判定是否拥有权限的决策方法
     *
     * @param authentication   是OauthUserDetailsServices中循环添加到 GrantedAuthority 对象中的权限信息集合.
     * @param object           当前的请求 路径 url
     * @param configAttributes 当前请求url 所需要的权限
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws CloudAccessDeniedException, InsufficientAuthenticationException {
        if(isNotRequest(object)){
            return;
        }
        boolean isAuthenticated = isAuthenticated(authentication); // 是否经过认证
        if (!isAuthenticated) {
            new CloudAccessDeniedException("没有权限");
        }
        List<String> attributes=CollectionConvertList(configAttributes);
        if(isPrimOrAuth(attributes)){
            return;
        }
        // 当前用户具有超级管理员权限直接通过
        if (isSupperAdmin(authentication)) {
            return;
        }
        boolean hasRole = cloudRolePermission.hasRole(authentication, object, configAttributes);
        if(hasRole){
            return;
        }
        List<String> permissionList=getPermission(authentication);

        attributes.stream().forEach(n->{
            boolean isMatch= permissionList.stream()
                    .anyMatch(x-> x.equals(n));
            if(isMatch){
                return;
            }
        });
        throw new CloudAccessDeniedException("没有权限");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    private boolean isAuthenticated(Authentication authentication) {
        return authentication.isAuthenticated();
    }

    private boolean isSupperAdmin(Authentication authentication) {
        boolean isSupperAdmin = authentication.getAuthorities().stream().anyMatch(n -> {
            String str = n.getAuthority();
            if ("ROLE_SUPPER_ADMIN".equals(str)) {
                return true;
            } else {
                return false;
            }
        });
        return isSupperAdmin;
    }
    private List<String> CollectionConvertList(Collection<ConfigAttribute> configAttributes){
        List<String> list=new ArrayList<>();
        configAttributes.stream().forEach(n->{
            list.add(n.toString());
        });
        return list;
    }
    private boolean isNoAttribute(Collection<ConfigAttribute> configAttributes){


        if(configAttributes==null || configAttributes.size()==0){
            return true;
        }
        return false;
    }
    private boolean isNotRequest(Object object){
        FilterInvocation filterInvocation=(FilterInvocation) object;
        if(filterInvocation.getRequestUrl().equals("/")){
            return true;
        }
        return false;
    }
    private boolean isPrimOrAuth(List<String> attributes){
        if(attributes.size()==1){
            if("permitAll".equals(attributes.get(0)) || "authenticated".equals(attributes.get(0))){
                return true;
            }
        }
        return false;
    }
    private List<String> getPermission(Authentication authentication){
        List<String> permissionList=new ArrayList<>();
        authentication.getAuthorities().stream().forEach(
                n->{
                    if(!n.getAuthority().startsWith(rolePrefix)){
                        permissionList.add(n.getAuthority());
                    }
                }
        );
        return permissionList;
    }

}
