package com.personal.microservice.limitservice.controller;

import com.personal.microservice.limitservice.model.Limits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @created 20/09/2022 - 10:41
 * @author quang
 * @project limit-service
 */
@RestController
@RequestMapping("/api/v1")
public class LimitController {

    private final AppProperties appProperties;

    @Autowired
    public LimitController(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @GetMapping("/limits")
    public Limits retrieveLimits() {
        return new Limits(appProperties.getMinimum(), appProperties.getMaximum());
    }
}
