package com.seiri.domains.task.controller;

import com.seiri.domains.task.dto.TaskDTO;
import com.seiri.domains.task.dto.TaskEditDTO;
import com.seiri.domains.task.dto.TaskResponseDTO;
import com.seiri.domains.task.dto.TaskResponseFullDTO;
import com.seiri.domains.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/new/{id}")
    public ResponseEntity<TaskResponseDTO> newTask(@PathVariable UUID id, @RequestBody @Valid TaskDTO taskDTO) {
        TaskResponseDTO response = this.taskService.newTask(id, taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable UUID id, @RequestBody @Valid TaskEditDTO taskDTO) {
        TaskResponseDTO response = this.taskService.updateTask(id, taskDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TaskResponseDTO> deleteTask(@PathVariable UUID id) {
        TaskResponseDTO response = this.taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/find/all/{id}")
    public ResponseEntity<List<TaskResponseFullDTO>> findAll(@PathVariable UUID id) {
        List<TaskResponseFullDTO> response = this.taskService.findAll(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<TaskResponseFullDTO> findById(@PathVariable UUID id) {
        TaskResponseFullDTO response = this.taskService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
