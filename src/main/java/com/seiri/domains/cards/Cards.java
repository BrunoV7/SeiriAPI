package com.seiri.domains.cards;

import com.seiri.domains.cards.dto.CardDTO;
import com.seiri.domains.cards.dto.CardEditDTO;
import com.seiri.domains.collumn.Collumn;
import com.seiri.domains.task.Task;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cards")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cards {

    // --- IDENTIFICATION ---
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // --- BASIC INFO ---
    @NotBlank
    @Size(max = 100)
    private String title;

    @Size(max = 500)
    private String description;

    private String body;

    private String issueKey;

    @NotNull
    private Integer position;

    private Integer priorityLevel;

    // --- STATUS AND DATES ---
    @NotNull
    private Boolean archived = false;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // --- RELATIONSHIPS ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "collumn_id", nullable = false)
    private Collumn collumn;

    @OneToMany(
            mappedBy = "card",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Task> tasks = new ArrayList<>();

    // --- ENTITY LIFECYCLE ---
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // --- HELPERS ---
    public Integer quantityOfTasks() {
        return tasks != null ? tasks.size() : 0;
    }

    public void addTask(Task task) {
        tasks.add(task);
        task.setCard(this);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        task.setCard(null);
    }

    // --- CONSTRUCTORS & MAPPERS ---
    public Cards(CardDTO cardDTO) {
        this.title = cardDTO.getTitle();
        this.description = cardDTO.getDescription();
        this.startDate = cardDTO.getStartDate();
        this.endDate = cardDTO.getEndDate();
        this.status = cardDTO.getStatus();
    }

    public void updateFromDTO(CardEditDTO cardDTO) {
        if (cardDTO == null) return;

        if (cardDTO.getTitle() != null && !cardDTO.getTitle().isBlank())
            this.title = cardDTO.getTitle();

        if (cardDTO.getDescription() != null)
            this.description = cardDTO.getDescription();

        if (cardDTO.getBody() != null)
            this.body = cardDTO.getBody();

        if (cardDTO.getIssueKey() != null)
            this.issueKey = cardDTO.getIssueKey();

        if (cardDTO.getPosition() != null)
            this.position = cardDTO.getPosition();

        if (cardDTO.getPriorityLevel() != null)
            this.priorityLevel = cardDTO.getPriorityLevel();

        if (cardDTO.getArchived() != null)
            this.archived = cardDTO.getArchived();

        if (cardDTO.getStartDate() != null)
            this.startDate = cardDTO.getStartDate();

        if (cardDTO.getEndDate() != null)
            this.endDate = cardDTO.getEndDate();

        if (cardDTO.getStatus() != null)
            this.status = cardDTO.getStatus();

    }

}
