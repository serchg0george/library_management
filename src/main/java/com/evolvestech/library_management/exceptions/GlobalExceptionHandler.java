package com.evolvestech.library_management.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException exception) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, response.status());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<InvalidDateExceptionResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        InvalidDateExceptionResponse errorResponse = new InvalidDateExceptionResponse(ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
