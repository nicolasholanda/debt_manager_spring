package com.github.nicolasholanda.debt.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<StandardError> objectNotFound(NoResultException e, HttpServletRequest request) {
        return ResponseEntity.status(NOT_FOUND).body(
                new StandardError(NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis())
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> constraintViolation(ConstraintViolationException e, HttpServletRequest request) {
        var message = e.getConstraintViolations()
                .stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(" | "));

        return ResponseEntity.status(BAD_REQUEST).body(
                new StandardError(BAD_REQUEST.value(), message, System.currentTimeMillis())
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgument(IllegalArgumentException e, HttpServletRequest request) {
        return ResponseEntity.status(BAD_REQUEST).body(
                new StandardError(BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis())
        );
    }
}
