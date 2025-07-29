package com.seiri.domains.board.dto;

import com.seiri.domains.board.Board;
import com.seiri.domains.collumn.dto.CollumnResponseFullDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseFullDTO {

    private UUID id;
    private String title;
    private String description;
    private Integer collumn_quantity;
    private List<CollumnResponseFullDTO> collumns;

    public BoardResponseFullDTO(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.description = board.getDescription();
        this.collumn_quantity = board.quantityOfCollumns();
        this.collumns = board.getCollumns().stream().map(CollumnResponseFullDTO::new).collect(Collectors.toList());
    }

}