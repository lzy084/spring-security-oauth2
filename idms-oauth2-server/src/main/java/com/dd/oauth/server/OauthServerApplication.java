package com.dd.oauth.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

@SpringBootApplication
@MapperScan(value ="com.dd.oauth.server.principal.dao")
//@ComponentScan(basePackages={"com.dd.oauth.server.*"})
public class OauthServerApplication {
    @Autowired
    DataSource dataSource;
    public static void main(String[] args) {
        SpringApplication.run(OauthServerApplication.class, args);
    }
    @Bean(name = "jdbcClientDetailsService")
    public JdbcClientDetailsService jdbcClientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }
}
