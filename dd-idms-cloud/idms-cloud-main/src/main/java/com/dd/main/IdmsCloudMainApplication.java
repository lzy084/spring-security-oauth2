package com.dd.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(value = {"com.dd.security.model.dao"})
@ComponentScan(value = {"com.dd.*","com.dd.common.*"})
@EnableFeignClients
public class IdmsCloudMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(IdmsCloudMainApplication.class,args);
    }
}
