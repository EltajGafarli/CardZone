package com.example.cardzone.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(
        name = "cashback-calculator",
        url = "https://cardzone-cashback-api-c2f5b8105e2b.herokuapp.com/api/"
)
public interface CashbackCalculation {

    @GetMapping(path = "cashback")
    Map<String, Double> calculateCashback(@RequestParam Double transactionAmount);
}
