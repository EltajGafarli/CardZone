package com.example.cardzone.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class TransactionDto {

    @PositiveOrZero(message = "amount must be greater than or equal to zero")
    private Double amount;

    @NotBlank(message = "transaction type can't be blank")
    @NotNull(message = "transaction type can't be null")
    @NotEmpty(message = "transaction type can't be empty")
    private String type;

    @NotNull(message = "hasCashback can't be null")
    private boolean hasCashback;
}
