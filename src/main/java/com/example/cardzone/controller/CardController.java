package com.example.cardzone.controller;

import com.example.cardzone.dto.CardRequestDto;
import com.example.cardzone.dto.CardsDto;
import com.example.cardzone.entity.Cards;
import com.example.cardzone.filter.CardSpecification;
import com.example.cardzone.mapper.CardMapper;
import com.example.cardzone.repository.CardRepository;
import com.example.cardzone.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    private final CardRepository cardRepository;


    private final CardMapper cardMapper;

    @GetMapping
    public Page<CardsDto> getAllCards(@RequestParam(required = false) Pageable pageable) {
        if (pageable == null) {
            pageable = Pageable.unpaged();
        }
        return cardService.getCards(pageable);
    }

    @GetMapping(path = "/{id}") // TODO: fix not found error
    public CardsDto getCardById(@PathVariable long id) {
        return cardService.getCardById(id);
    }


    @GetMapping("/filter")
    public List<CardsDto> filterCards(@RequestParam("cardID") long cardID, @RequestParam(value = "minBalance", required = false) Double minBalance, @RequestParam(value = "maxBalance", required = false) Double maxBalance) {
        Specification<Cards> specification = Specification
                .where(CardSpecification.filterByCustomerId(cardID))
                .and(CardSpecification.filterByMinBalance(minBalance))
                .and(CardSpecification.filterByMaxBalance(maxBalance));

        return cardRepository.findAll(specification)
                .stream()
                .map(cardMapper::cardToCardDto)
                .toList();
    }

    @PostMapping
    public ResponseEntity<String> createCard(@RequestBody @Valid CardRequestDto requestDto) {
        cardService.save(requestDto);
        return ResponseEntity.ok("Card created successfully");
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteCard(@PathVariable long id) {
        cardService.deleteById(id);

        return ResponseEntity.ok("Card deleted successfully");
    }

}
