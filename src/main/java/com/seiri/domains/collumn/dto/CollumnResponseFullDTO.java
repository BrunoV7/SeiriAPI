package com.seiri.domains.collumn.dto;

import com.seiri.domains.cards.dto.CardResponseFullDTO;
import com.seiri.domains.collumn.Collumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollumnResponseFullDTO {

    private UUID id;
    private String name;
    private Integer card_quantity;
    private List<CardResponseFullDTO> cards;
    private UUID board_id;


    public CollumnResponseFullDTO(Collumn collumn) {
        this.id = collumn.getId();
        this.name = collumn.getName();
        this.card_quantity = collumn.getCard_quantity();
        this.cards = collumn.getCards().stream().map(CardResponseFullDTO::new).collect(Collectors.toList());
        this.board_id = collumn.getBoard().getId();
    }
}
