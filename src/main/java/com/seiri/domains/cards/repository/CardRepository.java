package com.seiri.domains.cards.repository;

import com.seiri.domains.cards.Cards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Cards, UUID> {
    
    List<Cards> findAllByCollumn_Id(UUID collumnId);
    
}
