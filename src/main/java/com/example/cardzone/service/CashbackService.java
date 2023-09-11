package com.example.cardzone.service;

import com.example.cardzone.client.CashbackCalculation;
import com.example.cardzone.entity.Cards;
import com.example.cardzone.entity.Transactions;
import com.example.cardzone.repository.CardRepository;
import com.example.cardzone.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CashbackService {
    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;
    private final CashbackCalculation cashbackCalculation;

    @Transactional
    public void calculateCashbackAndUpdateBalance() {
        List<Transactions> transactionsList = transactionRepository.findAll();

        transactionsList.forEach(transaction -> {

            if (transaction.isHasCashback()) {
                Cards card = transaction.getCards();
                Double transactionAmount = transaction.getAmount();
                Map<String, Double> cashback = this.cashbackCalculation.calculateCashback(transactionAmount);
                Double cashbackAmount = cashback.get("cashbackAmount");
                card.setBalance(card.getBalance() + cashbackAmount);
                transaction.setHasCashback(false);
                cardRepository.save(card);
                transactionRepository.save(transaction);
            }
        });
    }
}
