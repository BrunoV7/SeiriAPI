package com.taskflow.domains.cards;

import com.taskflow.domains.board.Board;
import com.taskflow.domains.task.Task;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
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
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Task> tasks = new ArrayList<>();


}
