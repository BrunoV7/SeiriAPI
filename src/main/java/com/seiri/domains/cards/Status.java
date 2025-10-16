package com.seiri.domains.cards;

import com.seiri.domains.board.Board;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String title;

    @NotBlank
    private String colorCode;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public Status(String title, Board board) {
        this.title = title;
        this.board = board;
    }

    public Status(String title, String colorCode, Board board) {
        this.title = title;
        this.colorCode = colorCode;
        this.board = board;
    }

}
