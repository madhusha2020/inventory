package com.watersolution.inventory.component.common.validator;

import com.watersolution.inventory.component.common.validator.annotations.DateValidateConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateValidator implements ConstraintValidator<DateValidateConstraint, LocalDate> {

    @Override
    public void initialize(DateValidateConstraint dateValidateConstraint) {
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        return date.compareTo(LocalDate.now()) != 0;
    }
}
