package com.dd.cloud.security.conf;

import com.dd.cloud.core.base.security.PwdEncoder;
import com.dd.cloud.security.*;
import com.dd.cloud.security.filter.CloudFilterSecurityInterceptor;
import com.dd.cloud.security.model.service.SecurityUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityUserInfoService securityUserInfoService;
    @Autowired
    private CloudAccessDecisionManager cloudAccessDecisionManager;
    @Autowired
    private CloudSecurityMetadataSourceService cloudSecurityMetadataSourceService;
    @Autowired
    private CloudLoginSuccessHandler cloudLoginSuccessHandler;
    @Autowired
    private CloudLoginFailureHandler cloudLoginFailureHandler;
    @Autowired
    private CloudPermissionEvaluator cloudPermissionEvaluator;
    @Override
    @Bean
    public AuthenticationManager authenticationManager() {
        List<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(userAuthenticationProvider());
        CloudAuthenticationProviderManager oauthAuthenticationProviderManager =
                new CloudAuthenticationProviderManager(providers);
        return oauthAuthenticationProviderManager;
    }

    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(cloudPermissionEvaluator);
        return handler;
    }
//    @Bean
//    public CloudPermissionEvaluator oauthPermissionEvaluator(){
//        CloudPermissionEvaluator oauthPermissionEvaluator=new CloudPermissionEvaluator();
//        return oauthPermissionEvaluator;
//    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityUserInfoService).passwordEncoder(passwordEncoder());//配置用户认证方式以及加密方法
        auth.authenticationProvider(userAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**登录配置*/
        http.
                formLogin()
                .successHandler(cloudLoginSuccessHandler)
                .failureHandler(cloudLoginFailureHandler)
                .loginPage("/login.html")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/view/index.html","/login","/oauth/token")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .and()
                .authorizeRequests()
                .antMatchers("/sys/**").hasAnyRole("ADMIN","FINANCE","PEOPLE","ACTIVITI")
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()
                .and()
                .addFilter(cloudUsernamePasswordAuthenticationFilter())
                .addFilter(cloudFilterSecurityInterceptor())
                .anonymous()
                .disable()
                .authorizeRequests()
                .expressionHandler(webSecurityExpressionHandler())
                .accessDecisionManager(cloudAccessDecisionManager)
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated();
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint()).accessDeniedHandler(accessDeniedHandler());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/images/**",
                "/static/**",
                "/lib/**",
                "/js/**",
                "/layui/**",
                "/script/**",
                "/style/**",
                "/view/**",
                "/login.html"
        );
    }

    @Bean(name = "passwordEncoder")
    @Qualifier(value = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new PwdEncoder();
    }
    @Bean
    public CloudFilterSecurityInterceptor cloudFilterSecurityInterceptor() {
        CloudFilterSecurityInterceptor cloudFilterSecurityInterceptor=new
                CloudFilterSecurityInterceptor(cloudSecurityMetadataSourceService,cloudAccessDecisionManager);
        cloudFilterSecurityInterceptor.setSecurityMetadataSource(cloudSecurityMetadataSourceService);
        cloudFilterSecurityInterceptor.setAccessDecisionManager(cloudAccessDecisionManager);
        cloudFilterSecurityInterceptor.setAuthenticationManager(authenticationManager());
        return cloudFilterSecurityInterceptor;
    }

    @Bean
    public CloudUsernamePasswordAuthenticationFilter cloudUsernamePasswordAuthenticationFilter() throws Exception {
        CloudUsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new CloudUsernamePasswordAuthenticationFilter();
//        usernamePasswordAuthenticationFilter.setUserService(securityUserInfoService);
        usernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager());
        usernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(cloudLoginSuccessHandler);
        usernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(cloudLoginFailureHandler);
        return usernamePasswordAuthenticationFilter;
    }

    @Bean(name = "userAuthenticationProvider")
    @Qualifier(value = "userAuthenticationProvider")
    public UserAuthenticationProvider userAuthenticationProvider() {
        UserAuthenticationProvider userAuthenticationProvider = new UserAuthenticationProvider();
        userAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        userAuthenticationProvider.setSecurityUserInfoService(securityUserInfoService);
        return userAuthenticationProvider;
    }

    @Bean
    public SecurityContextHolderAwareRequestFilter securityContextHolderAwareRequestFilter() {
        return new SecurityContextHolderAwareRequestFilter();
    }
    @Bean
    public CloudAuthenticationEntryPoint authenticationEntryPoint(){
        return new CloudAuthenticationEntryPoint();
    }
    @Bean
    public CloudAccessDeniedHandler accessDeniedHandler(){
        return new CloudAccessDeniedHandler();
    }

}
