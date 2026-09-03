package com.bit.mango.salesmarketing.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * This ONE class catches errors for EVERY controller in the whole
 * app (@RestControllerAdvice means "apply this everywhere"), and
 * turns them into clean, consistent JSON instead of a scary Java
 * stack trace being sent straight to React.
 *
 * You don't call anything here yourself - Spring automatically runs
 * the matching @ExceptionHandler method whenever that type of error
 * is thrown anywhere in your Controllers/Services.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Triggered when a Service throws ResourceNotFoundException
    // (e.g. getById() for an id that doesn't exist) -> HTTP 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Triggered automatically when @Valid on a Controller method finds
    // invalid data (e.g. a required field is blank, or negative where
    // it shouldn't be) -> HTTP 400, with the exact field(s) that failed
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fe ->
                fieldErrors.put(fe.getField(), fe.getDefaultMessage())
        );
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                "One or more fields are invalid",
                fieldErrors
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Catch-all safety net: anything else unexpected -> HTTP 500,
    // but still as clean JSON instead of a raw stack trace.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
