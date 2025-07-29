package com.seiri.domains.task.dto;

import com.seiri.domains.task.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    @NotBlank
    private String title;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Status status;

} 