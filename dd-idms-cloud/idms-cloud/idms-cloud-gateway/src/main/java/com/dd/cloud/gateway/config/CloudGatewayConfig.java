//package com.dd.cloud.gateway.config;
//
//import com.netflix.hystrix.HystrixCommandProperties;
//import lombok.Builder;
//import org.springframework.cloud.client.circuitbreaker.Customizer;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//public class CloudGatewayConfig {
//    @Bean
//    public RouteLocator routes(RouteLocatorBuilder builder){
//        return builder.routes()
//                .route(r->r.path("/role/**").filters(f->f.stripPrefix(1).hystrix(config ->
//                        config.setName("default").setFallbackUri("forward:/fallback"))
//                ).uri("lb://idms-role").id("idms-role"))
//                .route(r->r.path("/menu/**").filters(f->f.stripPrefix(1).hystrix(config ->{
//                    config.setName("default");
//                    config.setFallbackUri("forward:/fallback");
//                })).uri("lb://idms-menu").id("idms-menu"))
//                .build();
//    }
//    @Bean
//    public Customizer<HystrixCircuitBreakerFactory> customizer(){
//        return factory -> factory.configure(builder -> builder.commandProperties(
//                HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(1)), "idms-role", "idms-menu");
//    }
//}
