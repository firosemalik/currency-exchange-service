package org.malik.micro.currency_exchange_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.malik.micro.currency_exchange_service.api.CurrencyExchange;
import org.malik.micro.currency_exchange_service.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository exchangeRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getExchangeValue(@PathVariable String from, @PathVariable String to) {
        log.info("GET Exchange called with from {} and to ", from, to);
        List<CurrencyExchange> currencyExchangeList = Collections.singletonList(exchangeRepository.findByFromAndTo(from, to));
        List<CurrencyExchange> currencyExchanges = currencyExchangeList.stream().map(e -> CurrencyExchange
                .builder().id(e.getId()).from(e.getFrom()).to(e.getTo())
                .conversionMultiple(e.getConversionMultiple())
                .environment(environment.getProperty("local.server.port")).build()).collect(Collectors.toList());
        return currencyExchanges.get(0);
    }

}
