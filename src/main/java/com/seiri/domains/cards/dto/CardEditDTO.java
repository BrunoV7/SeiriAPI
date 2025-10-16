package com.seiri.domains.cards.dto;

import com.seiri.domains.cards.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardEditDTO {

    private String title;
    private String description;
    private String body;
    private String issueKey;
    private Integer position;
    private Integer priorityLevel;
    private Boolean archived;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Status status;
    private UUID collumnId;
}
