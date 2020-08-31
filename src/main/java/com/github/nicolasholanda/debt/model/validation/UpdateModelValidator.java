package com.github.nicolasholanda.debt.model.validation;

import com.github.nicolasholanda.debt.model.BaseEntity;
import com.github.nicolasholanda.debt.model.dto.BaseDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.util.List;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class UpdateModelValidator implements ConstraintValidator<UpdateModel, Object[]> {

    @Override
    public void initialize(UpdateModel constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object[] objects, ConstraintValidatorContext constraintValidatorContext) {
        if(objects[0] != null && objects[1] != null) {
            if(!(objects[0] instanceof Number)) {
                throw new IllegalArgumentException("O ID enviado é inválido.");
            }

            var id = (Number) objects[0];

            if(objects[1] instanceof BaseDTO) {
                var dto = (BaseDTO<? extends Number>) objects[1];
                return dto.getId() != null && dto.getId().equals(id);
            } else if(objects[1] instanceof BaseEntity) {
                var entity = (BaseEntity<? extends Number>) objects[1];
                return entity.getId() != null && entity.getId().equals(id);
            } else {
                throw new IllegalArgumentException("O ID enviado é inválido.");
            }
        }

        return false;
    }
}
