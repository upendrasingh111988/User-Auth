package com.userServiceAuth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptonHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(Exception exception){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
