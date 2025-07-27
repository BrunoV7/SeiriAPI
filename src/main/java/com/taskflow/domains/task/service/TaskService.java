package com.taskflow.domains.task.service;

import com.taskflow.domains.cards.exception.NoCardFoundException;
import com.taskflow.domains.cards.service.CardService;
import com.taskflow.domains.task.Status;
import com.taskflow.domains.task.Task;
import com.taskflow.domains.task.dto.TaskDTO;
import com.taskflow.domains.task.dto.TaskEditDTO;
import com.taskflow.domains.task.dto.TaskResponseDTO;
import com.taskflow.domains.task.dto.TaskResponseFullDTO;
import com.taskflow.domains.task.exception.FailedToCreateTaskException;
import com.taskflow.domains.task.exception.InvalidTaskCreationParameters;
import com.taskflow.domains.task.exception.NoTaskFoundException;
import com.taskflow.domains.task.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final CardService cardService;

    public TaskService(TaskRepository taskRepository, CardService cardService) {
        this.taskRepository = taskRepository;
        this.cardService = cardService;
    }

    public TaskResponseDTO newTask(UUID id, @Valid TaskDTO taskDTO) {
        if(!this.cardService.existsById(id)) throw new NoCardFoundException();
        if(taskDTO == null) throw new InvalidTaskCreationParameters("Object was null");
        Task task = new Task(taskDTO);
        task.setCard(this.cardService.findByIdInternal(id));
        try {
            task = this.taskRepository.save(task);
        } catch (Exception e) {
            throw new FailedToCreateTaskException(e.getMessage());
        }
        return new TaskResponseDTO(task);
    }

    public TaskResponseDTO updateTask(UUID id, @Valid TaskEditDTO taskDTO) {
        if (!existsById(id)) throw new NoTaskFoundException("No task found with the selected Id");
        if (taskDTO == null) throw new InvalidTaskCreationParameters("Object was null");
        Task task = this.findByIdInternal(id);

        if (taskDTO.getTitle() != null && !taskDTO.getTitle().isBlank()) {
            task.setTitle(taskDTO.getTitle());
        }

        if (taskDTO.getDescription() != null && !taskDTO.getDescription().isBlank()) {
            task.setDescription(taskDTO.getDescription());
        }

        if (taskDTO.getStartDate() != null) {
            task.setStartDate(taskDTO.getStartDate());
        }

        if (taskDTO.getEndDate() != null) {
            task.setEndDate(taskDTO.getEndDate());
        }

        if (taskDTO.getStatus() != null) {
            task.setStatus(taskDTO.getStatus());
        }

        try {
            task = this.taskRepository.save(task);
        } catch (Exception e) {
            throw new FailedToCreateTaskException(e.getMessage());
        }

        return new TaskResponseDTO(task);
    }

    public TaskResponseDTO deleteTask(UUID id) {
        if(!this.existsById(id)) throw new NoTaskFoundException("No task found with the selected Id");
        Task task = this.findByIdInternal(id);
        this.taskRepository.delete(task);
        task.setStatus(Status.DELETED);
        return new TaskResponseDTO(task);
    }

    public List<TaskResponseFullDTO> findAll(UUID id) {
        return this.taskRepository.findAllByCard_Id(id).stream().map(TaskResponseFullDTO::new).collect(Collectors.toList());
    }

    public TaskResponseFullDTO findById(UUID id) {
        Task task = this.taskRepository.findById(id).orElse(null);
        if(task == null) throw new NoTaskFoundException("No task found with the selected Id");
        return new TaskResponseFullDTO(task);
    }

    public Task findByIdInternal(UUID id) {
        Task task = this.taskRepository.findById(id).orElse(null);
        if(task == null) throw new NoTaskFoundException("No task found with the selected Id");
        return task;
    }

    public Boolean existsById(UUID id) {
        return this.taskRepository.existsById(id);
    }
}
