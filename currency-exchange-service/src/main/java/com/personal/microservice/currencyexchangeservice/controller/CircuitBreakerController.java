package com.personal.microservice.currencyexchangeservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/*
 * @created 21/09/2022 - 13:36
 * @author quang
 * @project currency-exchange-service
 */
@RestController
@Slf4j
public class CircuitBreakerController {

    @GetMapping("/simple-api")
    // config retryNumber in application.yml ( default retry = 3)
    @Retry(name = "retry-number", fallbackMethod = "hardCodeResponse")
    public String simpleApi() {
        log.info("Simple api call received!");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:132", String.class);
        return forEntity.getBody();
    }

    @GetMapping("/simple-api-circuit")
    @CircuitBreaker(name = "default", fallbackMethod = "hardCodeResponse")
    public String simpleApiCircuitBreaker() {
        log.info("Simple api call received!");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:132", String.class);
        return forEntity.getBody();
    }

    public String hardCodeResponse(Exception e) {
        return "Fall back method";
    }
}
