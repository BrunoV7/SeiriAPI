package com.taskflow.domains.cards.dto;

import com.taskflow.domains.cards.Cards;
import com.taskflow.domains.cards.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.smartcardio.Card;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardResponseDTO {

    private UUID id;

    private String title;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Status status;

    private Integer numOfTasks;

    public CardResponseDTO(Cards card) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.description = card.getDescription();
        this.startDate = card.getStartDate();
        this.endDate = card.getEndDate();
        this.status = card.getStatus();
        this.numOfTasks = card.quantityOfTasks();
    }

}
