package com.example.cardzone.dto;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
@Builder
@ToString
public class CardRequestDto {

    @CreditCardNumber(ignoreNonDigitCharacters = true, message = "invalid card number")
    private String pan;

    @PositiveOrZero
    private long customerId;
}
