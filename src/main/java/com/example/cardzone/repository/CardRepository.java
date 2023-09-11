package com.example.cardzone.repository;

import com.example.cardzone.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Cards, Long>, JpaSpecificationExecutor<Cards> {

    @Query(value = "update Cards set balance=:newBalance where id=:id")
    void updateCardBalance(long id, double newBalance);
}
