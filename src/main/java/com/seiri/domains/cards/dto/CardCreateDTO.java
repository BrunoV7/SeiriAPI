package com.seiri.domains.cards.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CardCreateDTO {

    @NotBlank
    @Size(max = 100)
    private String title;

    @Size(max = 500)
    private String description;

    @NotNull
    private Integer position;

    private Integer priorityLevel = 0;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private Boolean archived = false;

    @NotNull
    private UUID collumnId;

    private UUID statusId;
}
