package com.seiri.domains.board;

import com.seiri.domains.collumn.Collumn;
import com.seiri.domains.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    private List<Collumn> collumns = new ArrayList<>();

    public Board(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public Integer quantityOfCollumns() {
        return collumns.size();
    }

    public boolean isOwner(User user) {
        return this.user.equals(user);
    }

}
