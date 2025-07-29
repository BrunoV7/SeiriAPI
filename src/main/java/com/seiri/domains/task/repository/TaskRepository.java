package com.seiri.domains.task.repository;

import com.seiri.domains.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    
    List<Task> findAllByCard_Id(UUID cardId);
    
} 