package com.watersolution.inventory.component.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateValidator implements ConstraintValidator<DateValidateConstraint, LocalDate> {

    @Override
    public void initialize(DateValidateConstraint dateValidateConstraint) {
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        return LocalDate.now().compareTo(date) != 0;
    }
}
