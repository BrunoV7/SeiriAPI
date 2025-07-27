package com.taskflow.domains.cards;

import com.taskflow.domains.board.Board;
import com.taskflow.domains.cards.dto.CardDTO;
import com.taskflow.domains.collumn.Collumn;
import com.taskflow.domains.task.Task;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @NotBlank
    public String title;

    public String description;

    public LocalDateTime startDate;

    public LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    public Status status = Status.BACKLOG;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collumn_id", nullable = false)
    private Collumn collumn;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Task> tasks = new ArrayList<>();

    public Integer quantityOfTasks(){
        return tasks.size();
    }

    public Cards(CardDTO cardDTO) {
        if (cardDTO.getTitle() != null && !cardDTO.getTitle().isBlank()) {
            this.title = cardDTO.getTitle();
        }

        if (cardDTO.getDescription() != null && !cardDTO.getDescription().isBlank()) {
            this.description = cardDTO.getDescription();
        }

        if (cardDTO.getStartDate() != null) {
            this.startDate = cardDTO.getStartDate();
        }

        if (cardDTO.getEndDate() != null) {
            this.endDate = cardDTO.getEndDate();
        }

        if (cardDTO.getStatus() != null) {
            this.status = cardDTO.getStatus();
        }
    }

}
