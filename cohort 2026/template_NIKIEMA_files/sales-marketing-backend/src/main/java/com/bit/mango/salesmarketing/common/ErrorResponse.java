package com.bit.mango.salesmarketing.common;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * The shape of every error your API sends back to React, e.g.:
 *
 * {
 *   "timestamp": "2026-08-31T17:10:00",
 *   "status": 404,
 *   "error": "Not Found",
 *   "message": "Customer not found with id: 999",
 *   "validationErrors": null
 * }
 *
 * Having ONE consistent shape for every error (instead of raw Java
 * stack traces) makes life much easier for whoever writes the React
 * side - they can always expect the same fields.
 */
public class ErrorResponse {
    private LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String error;
    private String message;
    private Map<String, String> validationErrors; // only filled in for validation failures

    public ErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public ErrorResponse(int status, String error, String message, Map<String, String> validationErrors) {
        this(status, error, message);
        this.validationErrors = validationErrors;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return message; }
    public Map<String, String> getValidationErrors() { return validationErrors; }
}
