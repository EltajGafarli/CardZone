package com.example.cardzone.scheduler;

import com.example.cardzone.service.CashbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CashbackScheduler {
    private final CashbackService cashbackService;

    @Scheduled(cron = "0 24 * * * *")
    public void cashbackTime() {
        cashbackService.calculateCashbackAndUpdateBalance();
    }
}
