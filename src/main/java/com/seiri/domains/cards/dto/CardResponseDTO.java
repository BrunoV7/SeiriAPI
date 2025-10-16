package com.seiri.domains.cards.dto;

import com.seiri.domains.cards.Cards;
import com.seiri.domains.cards.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardResponseDTO {

    private UUID id;
    private String title;
    private String description;
    private Integer position;
    private String body;
    private String issueKey;
    private Integer priorityLevel;
    private Boolean archived;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Status status;
    private UUID collumnId;
    private Integer numOfTasks;

    public CardResponseDTO(Cards card) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.description = card.getDescription();
        this.position = card.getPosition();
        this.priorityLevel = card.getPriorityLevel();
        this.issueKey = card.getIssueKey();
        this.archived = card.getArchived();
        this.startDate = card.getStartDate();
        this.body = card.getBody();
        this.endDate = card.getEndDate();
        this.status = card.getStatus();
        this.collumnId = card.getCollumn() != null ? card.getCollumn().getId() : null;
        this.numOfTasks = card.quantityOfTasks();
    }
}
