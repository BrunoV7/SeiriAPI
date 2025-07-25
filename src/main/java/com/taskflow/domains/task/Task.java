package com.taskflow.domains.task;

import com.taskflow.domains.cards.Cards;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import javax.smartcardio.Card;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @NotBlank
    public String title;

    @Enumerated(EnumType.STRING)
    public Status status = Status.PENDING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private Cards card;

}
