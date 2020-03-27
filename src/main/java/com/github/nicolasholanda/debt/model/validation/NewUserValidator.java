package com.github.nicolasholanda.debt.model.validation;

import com.github.nicolasholanda.debt.exception.FieldError;
import com.github.nicolasholanda.debt.model.dto.NewUserDTO;
import com.github.nicolasholanda.debt.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;

public class NewUserValidator implements ConstraintValidator<NewUser, NewUserDTO> {

    private ApplicationUserRepository repository;

    @Autowired
    public NewUserValidator(ApplicationUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(NewUser constraintAnnotation) {
    }

    @Override
    public boolean isValid(NewUserDTO dto, ConstraintValidatorContext context) {
        var errors = new ArrayList<FieldError>();

        if(repository.findByEmail(dto.getEmail()).isPresent()) {
            errors.add(new FieldError("email", "O e-mail já foi cadastrado."));
        }

        errors.forEach(e -> {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getField()).addConstraintViolation();
        });

        return errors.isEmpty();
    }
}
