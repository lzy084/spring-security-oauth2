package com.dd.cloud.uereka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
public class EurekaRegisterApp {
    public static void main(String[] args) {
        SpringApplication.run(EurekaRegisterApp.class,args);
    }
}
