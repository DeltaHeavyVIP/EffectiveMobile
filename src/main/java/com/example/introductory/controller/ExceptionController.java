package com.example.introductory.controller;

import com.example.introductory.exception.InvalidInputDataException;
import com.example.introductory.exception.PermissionDeniedException;
import com.example.introductory.exception.ResourceNotFoundException;
import jakarta.persistence.NonUniqueResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({InvalidInputDataException.class})
    public ResponseEntity<String> handleInvalidInputDataException(InvalidInputDataException invalidInputDataException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidInputDataException.getMessage());
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceNotFoundException.getMessage());
    }

    @ExceptionHandler({PermissionDeniedException.class})
    public ResponseEntity<String> handlePermissionDeniedException(PermissionDeniedException permissionDeniedException) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(permissionDeniedException.getMessage());
    }

    @ExceptionHandler({NonUniqueResultException.class})
    public ResponseEntity<String> handleNonUniqueResultException(NonUniqueResultException nonUniqueResultException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nonUniqueResultException.getMessage());
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException usernameNotFoundException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(usernameNotFoundException.getMessage());
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException badCredentialsException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badCredentialsException.getMessage());
    }

}
