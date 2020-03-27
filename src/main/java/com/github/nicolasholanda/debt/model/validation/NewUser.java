package com.github.nicolasholanda.debt.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NewUserValidator.class)
public @interface NewUser {
    String message() default "Usuário inválido.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
