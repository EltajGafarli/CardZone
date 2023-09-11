package com.example.cardzone.service;

import com.example.cardzone.dto.TransactionDto;
import com.example.cardzone.entity.Cards;
import com.example.cardzone.entity.Transactions;
import com.example.cardzone.exception.NotEnoughBalanceException;
import com.example.cardzone.mapper.TransactionMapper;
import com.example.cardzone.repository.CardRepository;
import com.example.cardzone.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;

    private final TransactionMapper transactionMapper;

    @Transactional
    public void doTransaction(long id, TransactionDto transactionDto) {
        Optional<Cards> cardById = cardRepository.findById(id);

        Cards card = cardById.orElseThrow();

        Transactions transactions = transactionMapper.transactionDtoToTransaction(transactionDto);

        String type = transactions.getType();


        Double balance = card.getBalance();
        Double amount = transactions.getAmount();

        if ("debit".equals(type)) {
            if (balance < amount) {
                throw new NotEnoughBalanceException("Balance is not enough");
            } else {
                card.setBalance(balance - amount);
            }
        } else {
            card.setBalance(balance + amount);
        }

        transactions.setCards(card);
        card.addTransaction(transactions);

        transactionRepository.save(transactions);
        cardRepository.save(card);

    }
}
