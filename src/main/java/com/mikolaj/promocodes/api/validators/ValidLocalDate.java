package com.mikolaj.promocodes.api.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = LocalDateValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLocalDate {
    String message() default "Invalid date format (dd-MM-yyyy)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

