package com.seiri.domains.cards.repository;

import com.seiri.domains.board.Board;
import com.seiri.domains.cards.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface StatusRepository extends JpaRepository<Status, UUID> {

    List<Status> findAllByBoard(Board board);

}
