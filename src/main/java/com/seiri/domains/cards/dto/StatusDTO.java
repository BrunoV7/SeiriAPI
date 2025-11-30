package com.seiri.domains.cards.dto;

import com.seiri.domains.cards.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDTO {
    private UUID id;
    private String title;
    private String colorCode;
    private UUID board_id;

    public StatusDTO(Status status) {
        this.id = status.getId();
        this.title = status.getTitle();
        this.colorCode = status.getColorCode();
        this.board_id = status.getBoard().getId();
    }

}
