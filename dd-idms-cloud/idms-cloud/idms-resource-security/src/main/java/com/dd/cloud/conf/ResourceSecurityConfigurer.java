package com.dd.cloud.conf;

import com.dd.cloud.ResourceAccessDeniedHandler;
import com.dd.cloud.ResourceEntryPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

@Slf4j
@Configuration
@EnableResourceServer
public class ResourceSecurityConfigurer extends ResourceServerConfigurerAdapter {
    @Value("${check_endpoint_url}")
    String checkUrl;
    @Value("${client_id}")
    String clientId;
    @Value("${client_secret}")
    String clientSecret;
    @Value("${resource_id}")
    String resourceId;

    @Resource
    private RedisConnectionFactory oauthRedisConnectionFactory;


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .resourceId(resourceId)
                .tokenServices(remoteTokenServices())
                .accessDeniedHandler(new ResourceAccessDeniedHandler())
                .authenticationEntryPoint(new ResourceEntryPoint()).tokenStore(tokenStore())
                .tokenStore(tokenStore());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .anyRequest()
                .authenticated().and()
                .anonymous().disable()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public RemoteTokenServices remoteTokenServices() {
        final RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl(checkUrl);
        tokenService.setClientId(clientId);
        tokenService.setClientSecret(clientSecret);
        return tokenService;
    }

    @Bean(name = "tokenStore")
    @Qualifier(value = "tokenStore")
    public TokenStore tokenStore() {
        RedisTokenStore redisTokenStore = new RedisTokenStore(oauthRedisConnectionFactory);
        return redisTokenStore;
    }

}
