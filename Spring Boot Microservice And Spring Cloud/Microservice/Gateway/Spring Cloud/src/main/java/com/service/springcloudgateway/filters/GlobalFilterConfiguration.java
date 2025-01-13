package com.service.springcloudgateway.filters;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

@Configuration
public class GlobalFilterConfiguration {

    @Bean
    @Order(1)
    public GlobalFilter secondFilterPrePost(){
        return (exchange, chain) -> {
            System.out.println("secondFilterPre Executed");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("secondFilterPost Executed");
            }));
        };
    }

    @Bean
    @Order(2)
    public GlobalFilter thirdFilterPrePost(){
        return (exchange, chain) -> {
            System.out.println("thirdFilterPre Executed");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("thirdFilterPost Executed");
            }));
        };
    }

    @Bean
    @Order(3)
    public GlobalFilter fourthFilterPrePost(){
        return (exchange, chain) -> {
            System.out.println("fourthFilterPre Executed");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("fourthFilterPost Executed");
            }));
        };
    }
}
