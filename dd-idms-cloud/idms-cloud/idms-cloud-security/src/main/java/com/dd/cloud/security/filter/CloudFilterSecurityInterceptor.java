package com.dd.cloud.security.filter;

import com.dd.cloud.security.CloudSecurityMetadataSourceService;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class CloudFilterSecurityInterceptor extends FilterSecurityInterceptor {
    private CloudSecurityMetadataSourceService cloudSecurityMetadataSourceService;
    AccessDecisionManager accessDecisionManager;
    public CloudFilterSecurityInterceptor(CloudSecurityMetadataSourceService cloudSecurityMetadataSourceService
    ,AccessDecisionManager accessDecisionManager
    ){
        this.cloudSecurityMetadataSourceService=cloudSecurityMetadataSourceService;
        this.accessDecisionManager=accessDecisionManager;
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi);
    }
    @Override
    public void invoke(FilterInvocation filterInvocation) throws IOException, ServletException {
        InterceptorStatusToken token = super.beforeInvocation(filterInvocation);

        try {
            filterInvocation.getChain().doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
        } finally {
            super.finallyInvocation(token);
        }
        super.afterInvocation(token, filterInvocation.getRequestUrl());
    }
    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.cloudSecurityMetadataSourceService;
    }

    @Override
    public void setAccessDecisionManager(AccessDecisionManager accessDecisionManager) {
      super.setAccessDecisionManager(accessDecisionManager);
    }

    @Override
    public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource){
        super.setSecurityMetadataSource(securityMetadataSource);
    }
}
