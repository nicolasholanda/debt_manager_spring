package com.github.nicolasholanda.debt.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.stream.Collectors;

import static java.lang.String.format;
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

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityViolationException e, HttpServletRequest request) {
        return ResponseEntity.status(BAD_REQUEST).body(
                new StandardError(BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis())
        );
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<StandardError> propertyReference(PropertyReferenceException e, HttpServletRequest request) {
        return ResponseEntity.status(BAD_REQUEST).body(
                new StandardError(BAD_REQUEST.value(),
                        format("O campo de ordenação %s não existe.", e.getPropertyName()),
                        System.currentTimeMillis())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        var message = e.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(" | "));

        return ResponseEntity.status(BAD_REQUEST).body(
                new StandardError(BAD_REQUEST.value(), message, System.currentTimeMillis())
        );
    }
}
