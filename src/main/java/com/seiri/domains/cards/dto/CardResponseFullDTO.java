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

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Status status;

    private Integer numOfTasks;

    private List<TaskResponseDTO> tasks;

    public CardResponseFullDTO(Cards card) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.description = card.getDescription();
        this.startDate = card.getStartDate();
        this.endDate = card.getEndDate();
        this.status = card.getStatus();
        this.numOfTasks = card.quantityOfTasks();
        this.tasks = card.getTasks().stream().map(TaskResponseDTO::new).collect(Collectors.toList());
    }

}
