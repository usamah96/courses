package com.service.springcloudgateway.security;

import io.jsonwebtoken.Jwts;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    public AuthorizationHeaderFilter(){
        super(Config.class);
    }

    static class Config{}

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest req = exchange.getRequest();
            if(req.getHeaders().containsKey("Authorization")){
                String token = Objects.requireNonNull(req.getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
                if(!isJwtValid(token)) return onError(exchange, "Invalid Token", HttpStatus.UNAUTHORIZED);

            } else return onError(exchange, "No Authorization Header", HttpStatus.UNAUTHORIZED);
            return chain.filter(exchange);
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus status){
        ServerHttpResponse res = exchange.getResponse();
        res.setStatusCode(status);
        return res.setComplete();
    }

    private boolean isJwtValid(String jwt){
        try{
            String userId = Jwts.parser().setSigningKey("abc123def456").parseClaimsJws(jwt).getBody().getSubject();
            return userId != null && !userId.isEmpty();
        } catch(Exception e){
            return false;
        }
    }
}
