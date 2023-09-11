package com.example.cardzone.service;

import com.example.cardzone.dto.CardRequestDto;
import com.example.cardzone.dto.CardsDto;
import com.example.cardzone.entity.Cards;
import com.example.cardzone.exception.CardNotFoundException;
import com.example.cardzone.mapper.CardMapper;
import com.example.cardzone.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;


    public Page<CardsDto> getCards(Pageable pageable) {
        Page<Cards> cards = cardRepository.findAll(pageable);
        return cards.map(cardMapper::cardToCardDto);
    }

    public CardsDto getCardById(long id) {
        Optional<Cards> cardById = cardRepository.findById(id);

        Cards card = cardById.orElseThrow(() -> new CardNotFoundException("Card not found"));

        return cardMapper.cardToCardDto(card);
    }

    @Transactional
    public void save(CardRequestDto requestDto) {
        Cards cards = cardMapper.cardRequestToCard(requestDto);
        cardRepository.save(cards);
    }

    @Transactional
    public void deleteById(long id) {
        cardRepository.findById(id).orElseThrow(() -> new CardNotFoundException("Card not found"));
        cardRepository.deleteById(id);
    }


}
