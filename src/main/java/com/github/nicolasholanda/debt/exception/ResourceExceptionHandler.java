package com.github.nicolasholanda.debt.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.web.bind.MissingServletRequestParameterException;

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

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<StandardError> validation(TransactionSystemException e, HttpServletRequest request) {
        if(e.getRootCause() != null && e.getRootCause() instanceof ConstraintViolationException) {
            return constraintViolation((ConstraintViolationException) e.getRootCause(), request);
        }

        var message = e.getLocalizedMessage();

        return ResponseEntity.status(BAD_REQUEST).body(
                new StandardError(BAD_REQUEST.value(), message, System.currentTimeMillis())
        );
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<StandardError> invalidFormat(InvalidFormatException e, HttpServletRequest request) {
        var message = "O JSON enviado possui campos fora do padrão. Consulte a documentação.";

        return ResponseEntity.status(BAD_REQUEST).body(
                new StandardError(BAD_REQUEST.value(), message, System.currentTimeMillis())
        );
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<StandardError> jsonMapping(JsonMappingException e, HttpServletRequest request) {
        var message = "Erro ao mapear o JSON para objeto.";
        var cause = e.getCause();

        if(cause != null) {
            message = cause.getMessage();
        }

        return ResponseEntity.status(BAD_REQUEST).body(
                new StandardError(BAD_REQUEST.value(), message, System.currentTimeMillis())
        );
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<StandardError> jsonMapping(MissingServletRequestParameterException e, HttpServletRequest request) {
        var message = format("O parâmetro obrigatório %s não foi enviado.", e.getParameterName());

        return ResponseEntity.status(BAD_REQUEST).body(
                new StandardError(BAD_REQUEST.value(), message, System.currentTimeMillis())
        );
    }
}
