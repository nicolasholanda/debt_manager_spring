package com.github.nicolasholanda.debt.model.validation;

import com.github.nicolasholanda.debt.exception.FieldError;
import com.github.nicolasholanda.debt.model.dto.ExistentUserDTO;
import com.github.nicolasholanda.debt.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;

public class ExistentUserValidator implements ConstraintValidator<ExistentUser, ExistentUserDTO> {

    private ApplicationUserRepository repository;

    @Autowired
    public ExistentUserValidator(ApplicationUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(ExistentUser constraintAnnotation) {
    }

    @Override
    public boolean isValid(ExistentUserDTO dto, ConstraintValidatorContext context) {
        var errors = new ArrayList<FieldError>();

        repository.findByEmail(dto.getEmail()).ifPresent((user) -> {
            if(!user.getId().equals(dto.getId())) {
                errors.add(new FieldError("email", "Este e-mail é utilizado por outro usuário."));
            }
        });

        errors.forEach(e -> {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getField()).addConstraintViolation();
        });

        return errors.isEmpty();
    }
}
