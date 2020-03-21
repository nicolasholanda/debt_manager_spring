package com.github.nicolasholanda.debt.exception;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<StandardError> objectNotFound(NoResultException e, HttpServletRequest request) {
        return ResponseEntity.status(NOT_FOUND).body(
                new StandardError(NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis())
        );
    }
}
