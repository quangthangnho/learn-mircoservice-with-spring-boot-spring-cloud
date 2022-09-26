package com.personal.microservice.currencyexchangeservice.controller;

import com.personal.microservice.currencyexchangeservice.entity.CurrencyExchange;
import com.personal.microservice.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.SQLException;

/*
 * @created 20/09/2022 - 14:09
 * @author quang
 * @project currency-exchange-service
 */
@RestController
@RequestMapping("/api/v1/")
public class CurrencyExchangeController {

    private final Environment environment;
    private final CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    public CurrencyExchangeController(Environment environment, CurrencyExchangeRepository currencyExchangeRepository) {
        this.environment = environment;
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveChangeValue(@PathVariable String from,
                                                @PathVariable String to) throws SQLException {
        String port = environment.getProperty("local.server.port");
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
        if(currencyExchange == null) {
            throw new RuntimeException("Unable to find data for : " + from + " to " + to);
        }
        currencyExchange.setEnviroment(port);
        return currencyExchange;
    }

}
