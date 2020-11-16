package com.watersolution.inventory.component.common.validator;

import com.watersolution.inventory.component.common.validator.annotations.FutureDateValidateConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class FutureDateValidator implements ConstraintValidator<FutureDateValidateConstraint, LocalDate> {

    @Override
    public void initialize(FutureDateValidateConstraint futureDateValidateConstraint) {
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        return date.isAfter(LocalDate.now());
    }
}
