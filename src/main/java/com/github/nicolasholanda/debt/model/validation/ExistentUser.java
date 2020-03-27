package com.github.nicolasholanda.debt.model.validation;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistentUserValidator.class)
public @interface ExistentUser {
    String message() default "Não foi possível atualizar o usuário.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
