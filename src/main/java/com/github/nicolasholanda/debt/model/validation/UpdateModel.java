package com.github.nicolasholanda.debt.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UpdateModelValidator.class)
public @interface UpdateModel {
    String message() default "Objeto inválido para atualização.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
