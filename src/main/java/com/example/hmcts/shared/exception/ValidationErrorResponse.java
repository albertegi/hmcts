package com.example.hmcts.shared.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Validation error response for REST API.
 */
@Data
@Builder
public class ValidationErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private Map<String, String> validationErrors;
}

