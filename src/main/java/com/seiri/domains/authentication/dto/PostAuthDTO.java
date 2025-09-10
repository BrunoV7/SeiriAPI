package com.seiri.domains.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostAuthDTO {
    private UUID sessionId;
    private String token;
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String avatarUrl;

}
