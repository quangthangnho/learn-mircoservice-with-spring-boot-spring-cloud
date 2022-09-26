package com.personal.microservice.currencyexchangeservice.repository;

import com.personal.microservice.currencyexchangeservice.entity.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;

/*
 * @created 20/09/2022 - 15:34
 * @author quang
 * @project currency-exchange-service
 */
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

    CurrencyExchange findByFromAndTo(String from, String to) throws SQLException;
}
