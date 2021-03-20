package com.dd.oauth.server.configuration;

import com.dd.user.service.SecurityUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Autowired
    SecurityUserInfoService securityUserInfoService;
    @Autowired
    JdbcClientDetailsService jdbcClientDetailsService;
//    @Autowired
//    PasswordEncoder passwordEncoder;

    @Value("${spring.security.oauth2.resourceserver.opaquetoken.introspection-uri}")
    String introspectionUri;
    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-id}")
    String clientId;
    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-secret}")
    String clientSecret;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.debug("4");
        http.authorizeRequests(n -> n.anyRequest().authenticated())
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.opaqueToken(
                        opaqueToken -> opaqueToken.authenticationManager(oAuth2AuthorizationManager())
                ));
    }

    public OAuth2AuthenticationManager oAuth2AuthorizationManager(){
        OAuth2AuthenticationManager oAuth2AuthorizationManager=new OAuth2AuthenticationManager();
        oAuth2AuthorizationManager.setClientDetailsService(jdbcClientDetailsService);
        oAuth2AuthorizationManager.setTokenServices(remoteTokenServices());
        oAuth2AuthorizationManager.setResourceId("oauth2-Server");
        return oAuth2AuthorizationManager;
    }
    @Bean
    public RemoteTokenServices remoteTokenServices() {
        final RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl(introspectionUri);
        tokenService.setClientId(clientId);
        tokenService.setClientSecret(clientSecret);
        return tokenService;
    }

}
