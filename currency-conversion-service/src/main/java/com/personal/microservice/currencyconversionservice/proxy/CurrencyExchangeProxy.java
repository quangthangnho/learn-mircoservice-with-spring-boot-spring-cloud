package com.personal.microservice.currencyconversionservice.proxy;

import com.personal.microservice.currencyconversionservice.model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;

/*
 * @created 20/09/2022 - 16:45
 * @author quang
 * @project currency-conversion-service
 */
@FeignClient(name = "currency-exchange-service")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/api/v1/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveChangeValue(
            @PathVariable String from,
            @PathVariable String to) throws SQLException;
}
