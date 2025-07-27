package com.taskflow.domains.collumn.repository;

import com.taskflow.domains.collumn.Collumn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CollumnRepository extends JpaRepository<Collumn, UUID> {
    
    List<Collumn> findAllByBoard_Id(UUID boardId);
    
}
