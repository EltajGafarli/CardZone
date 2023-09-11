package com.example.cardzone.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
@Builder
@ToString
public class CardsDto implements Serializable {
    private long id;
    private String pan;
    private long customerId;
    private Double balance;
}
