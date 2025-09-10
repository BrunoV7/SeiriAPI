package com.seiri.domains.board.repository;

import com.seiri.domains.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BoardRepository extends JpaRepository<Board, UUID> {
    Board getBoardById(UUID id);

    List<Board> getBoardsByUserId(UUID userId);
}
