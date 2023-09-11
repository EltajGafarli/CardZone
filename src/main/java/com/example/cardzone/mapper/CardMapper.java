package com.example.cardzone.mapper;

import com.example.cardzone.dto.CardRequestDto;
import com.example.cardzone.dto.CardsDto;
import com.example.cardzone.entity.Cards;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {
    public CardsDto cardToCardDto(Cards card) {
        CardsDto cardsDto = new CardsDto();
        BeanUtils.copyProperties(card, cardsDto);
        return cardsDto;
    }

    public Cards cardDtoToCard(CardsDto cardsDto) {
        Cards card = new Cards();
        BeanUtils.copyProperties(cardsDto, card);

        return card;
    }

    public Cards cardRequestToCard(CardRequestDto dto) {

        return Cards
                .builder()
                .balance(0.0)
                .pan(dto.getPan())
                .customerId(dto.getCustomerId())
                .build();
    }
}
