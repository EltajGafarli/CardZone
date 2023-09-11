package com.example.cardzone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cards")

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class Cards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "pan", unique = true)
    private String pan;

    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "balance", columnDefinition = "numeric(10, 2)")
    private Double balance;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cards")
    @ToString.Exclude
    @JsonIgnore
    private Set<Transactions> transactions = new HashSet<>();

    public void addTransaction(Transactions transaction) {
        transactions.add(transaction);
    }
}
