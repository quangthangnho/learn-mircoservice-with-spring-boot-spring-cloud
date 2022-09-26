package com.personal.microservice.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @created 21/09/2022 - 11:16
 * @author quang
 * @project api-gateway
 */
@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder route) {
        return route
                .routes()
                .route(p -> p
                        .path("/get")
                        .filters(f -> f
                                // config url with header and param
                                .addRequestHeader("Myheader", "headerValue")
                                .addRequestParameter("Param", "paramValue"))
                        .uri("http://httpbin.org:80"))
                .route(p -> p
                        .path("/currency-exchange/**")
                        .uri("lb://currency-exchange-service"))
                .route(p -> p
                        // ex: http://localhost:8765/currency-conversion/api/v1/currency-conversion-feign/from/USD/to/VND/quantity/10
                        .path("/currency-conversion/**")
                        // no se tu dong call qua service dua theo service name tren eureka ( currency-conversion-service) tu dong loadbalancer
                        .uri("lb://currency-conversion-service"))
                .build();
    }
}
