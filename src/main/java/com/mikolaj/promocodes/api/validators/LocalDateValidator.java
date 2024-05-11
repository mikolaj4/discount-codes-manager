package com.mikolaj.promocodes.api.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateValidator implements ConstraintValidator<ValidLocalDate, String> {

    @Override
    public void initialize(ValidLocalDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        try {
            LocalDate.parse(value, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
