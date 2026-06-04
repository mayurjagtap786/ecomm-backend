package org.shopping_kart.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class RequestTraceFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeader = exchange.getRequest().getHeaders();
        if(isCorrelationIdPresent(requestHeader)){
            //logger.debug()
        }
        return null;
    }

    private boolean isCorrelationIdPresent(HttpHeaders requestHeaders){

    }
}
