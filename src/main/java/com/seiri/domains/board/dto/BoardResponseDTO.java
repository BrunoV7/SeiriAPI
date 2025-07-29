package com.seiri.domains.board.dto;

import com.seiri.domains.board.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseDTO {

    private UUID id;
    private String title;
    private String description;
    private Integer collumn_quantity;

    public BoardResponseDTO(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.description = board.getDescription();
        this.collumn_quantity = board.quantityOfCollumns();
    }

}