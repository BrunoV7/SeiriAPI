package com.taskflow.domains.board;

import com.taskflow.domains.cards.Cards;
import com.taskflow.domains.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String title;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Cards> cards = new ArrayList<>();

}
