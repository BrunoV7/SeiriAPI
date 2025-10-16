package com.seiri.domains.cards.dto;

import com.seiri.domains.cards.Cards;
import com.seiri.domains.cards.Status;
import com.seiri.domains.task.dto.TaskResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardResponseFullDTO {

    private UUID id;
    private String title;
    private String description;
    private String body;
    private String issueKey;
    private Integer position;
    private Integer priorityLevel;
    private Boolean archived;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Status status;
    private UUID collumnId;
    private Integer numOfTasks;
    private List<TaskResponseDTO> tasks;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public CardResponseFullDTO(Cards card) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.description = card.getDescription();
        this.body = card.getBody();
        this.issueKey = card.getIssueKey();
        this.position = card.getPosition();
        this.priorityLevel = card.getPriorityLevel();
        this.archived = card.getArchived();
        this.startDate = card.getStartDate();
        this.endDate = card.getEndDate();
        this.status = card.getStatus();
        this.collumnId = card.getCollumn() != null ? card.getCollumn().getId() : null;
        this.numOfTasks = card.quantityOfTasks();
        this.tasks = card.getTasks().stream()
                .map(TaskResponseDTO::new)
                .collect(Collectors.toList());
        this.createdDate = card.getCreatedAt();
        this.updatedDate = card.getUpdatedAt();
    }
}
