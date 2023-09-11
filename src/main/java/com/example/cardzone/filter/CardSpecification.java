package com.example.cardzone.filter;

import com.example.cardzone.entity.Cards;
import org.springframework.data.jpa.domain.Specification;

public class CardSpecification {

    public static Specification<Cards> filterByCustomerId(long customerID) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("customerId"), customerID);
    }

    public static Specification<Cards> filterByMinBalance(Double minBalance) {
        if (minBalance == null) {
            minBalance = 0.0;
        }
        Double finalMinBalance = minBalance;
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("balance"), finalMinBalance);
    }

    public static Specification<Cards> filterByMaxBalance(Double maxBalance) {
        if (maxBalance == null) {
            maxBalance = Double.MAX_VALUE;
        }
        Double finalMaxBalance = maxBalance;
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("balance"), finalMaxBalance);
    }
}
