package com.dd.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(value = {"com.dd.*"})
@MapperScan(value = {"com.dd.order.dao"})
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate rest = new RestTemplate();
        rest.getInterceptors().add((request, body, execution) -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                return execution.execute(request, body);
            }
            if (!(authentication.getCredentials() instanceof AbstractOAuth2Token)) {
                return execution.execute(request, body);
            }
            AbstractOAuth2Token token = (AbstractOAuth2Token) authentication.getCredentials();
            request.getHeaders().setBearerAuth(token.getTokenValue());
            return execution.execute(request, body);
        });
        return rest;
    }
}
