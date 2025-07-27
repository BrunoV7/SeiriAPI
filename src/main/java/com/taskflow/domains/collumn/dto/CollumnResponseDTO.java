package com.taskflow.domains.collumn.dto;

import com.taskflow.domains.collumn.Collumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollumnResponseDTO {

    private UUID id;
    private String name;
    private Integer card_quantity;
    private UUID board_id;


    public CollumnResponseDTO(Collumn collumn) {
        this.id = collumn.getId();
        this.name = collumn.getName();
        this.card_quantity = collumn.getCard_quantity();
        this.board_id = collumn.getBoard().getId();
    }
}
