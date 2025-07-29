package com.seiri.infra;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class DefaultErrorDTO {

    private HttpStatus status;
    private String errorId;
    private String message;
    private String details;

    public DefaultErrorDTO(HttpStatus status, String errorId, String message, String details) {
        this.status = status;
        this.errorId = errorId;
        this.message = message;
        this.details = details;
    }

    public DefaultErrorDTO(DefaultErrorDTO defaultErrorDTO) {
        this.status = defaultErrorDTO.status;
        this.errorId = defaultErrorDTO.errorId;
        this.message = defaultErrorDTO.message;
        this.details = defaultErrorDTO.details;
    }

    public DefaultErrorDTO() {}

}
