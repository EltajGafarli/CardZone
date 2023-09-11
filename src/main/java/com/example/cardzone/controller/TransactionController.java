package com.example.cardzone.controller;

import com.example.cardzone.dto.TransactionDto;
import com.example.cardzone.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/cards")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping(path = "/{id}/transactions")
    public ResponseEntity<String> doTransaction(@PathVariable("id") long id, @RequestBody @Valid TransactionDto transactionDto) {
        System.out.println(transactionDto);
        transactionService.doTransaction(id, transactionDto);

        return ResponseEntity.ok("transaction doing successfully");
    }
}
