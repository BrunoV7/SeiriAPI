package com.taskflow.domains.task;

import com.taskflow.domains.cards.Cards;
import com.taskflow.domains.task.dto.TaskDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @NotBlank
    public String title;

    public String description;

    public LocalDateTime startDate;

    public LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    public Status status = Status.PENDING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private Cards card;

    public Task(TaskDTO taskDTO) {
        if (taskDTO.getTitle() != null && !taskDTO.getTitle().isBlank()) {
            this.title = taskDTO.getTitle();
        }

        if (taskDTO.getDescription() != null && !taskDTO.getDescription().isBlank()) {
            this.description = taskDTO.getDescription();
        }

        if (taskDTO.getStartDate() != null) {
            this.startDate = taskDTO.getStartDate();
        }

        if (taskDTO.getEndDate() != null) {
            this.endDate = taskDTO.getEndDate();
        }

        if (taskDTO.getStatus() != null) {
            this.status = taskDTO.getStatus();
        }
    }

}
