package com.dd.oauth.server.configuration;

import com.dd.cloud.core.base.security.PwdEncoder;
import com.dd.oauth.server.principal.service.SecurityUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * 授权服务器
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    RedisConnectionFactory oauthRedisConnectionFactory;
    @Autowired
    SecurityUserInfoService securityUserInfoService;
    @Autowired
    JdbcClientDetailsService jdbcClientDetailsService;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients()
                .passwordEncoder(new PwdEncoder())
                .addTokenEndpointAuthenticationFilter(new CorsFilter(corsConfigurationSource()));
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).passwordEncoder(new PwdEncoder());
        clients.withClientDetails(jdbcClientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager()).userDetailsService(securityUserInfoService);
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.POST, HttpMethod.GET);
        endpoints.tokenStore(tokenStore());
        endpoints.tokenEnhancer(tokenEnhancer());
        endpoints.tokenServices(defaultTokenServices());
        endpoints.setClientDetailsService(jdbcClientDetailsService);
    }

    @Bean(name = "tokenStore")
    @Qualifier(value = "tokenStore")
    public TokenStore tokenStore() {
        RedisTokenStore redisTokenStore = new RedisTokenStore(oauthRedisConnectionFactory);
        return redisTokenStore;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        return tokenEnhancerChain;
    }
    @Bean("authenticationManager")
    @Qualifier("authenticationManager")
    public OAuth2AuthorizationManager authenticationManager() {
        List<AuthenticationProvider> providers=new ArrayList<>();
        providers.add(userAuthenticationProvider());
        OAuth2AuthorizationManager oAuth2AuthorizationManager=new OAuth2AuthorizationManager(providers);
        return oAuth2AuthorizationManager;
    }
    @Bean(name = "userAuthenticationProvider")
    @Qualifier(value = "userAuthenticationProvider")
    public UserAuthenticationProvider userAuthenticationProvider() {
        UserAuthenticationProvider userAuthenticationProvider = new UserAuthenticationProvider();
        userAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        userAuthenticationProvider.setUserDetailsService(securityUserInfoService);
        return userAuthenticationProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PwdEncoder();
    }

    @Bean
    @Primary
    public AuthorizationServerTokenServices defaultTokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setAccessTokenValiditySeconds(60*90);
        defaultTokenServices.setRefreshTokenValiditySeconds(60*120);
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

    /**
     * 跨域请求配置
     *
     * @return
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        return httpServletRequest -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.addAllowedHeader("*");
            corsConfiguration.addAllowedOrigin(httpServletRequest.getHeader(HttpHeaders.ORIGIN));
            corsConfiguration.addAllowedMethod("*");
            corsConfiguration.setAllowCredentials(true);
            corsConfiguration.setMaxAge(3600L);
            return corsConfiguration;
        };
    }
}
