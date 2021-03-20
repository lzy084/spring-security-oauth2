package com.dd.cloud.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class CloudGatewayFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //exchange.getAttribute();
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }

//    @Override
//    public ShortcutType shortcutType() {
//        return null;
//    }
//
//    @Override
//    public List<String> shortcutFieldOrder() {
//        return null;
//    }
//
//    @Override
//    public String shortcutFieldPrefix() {
//        return null;
//    }
}
