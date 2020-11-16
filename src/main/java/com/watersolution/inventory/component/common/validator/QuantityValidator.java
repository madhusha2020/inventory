package com.watersolution.inventory.component.common.validator;

import com.watersolution.inventory.component.common.validator.annotations.QuantityValidateConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class QuantityValidator implements ConstraintValidator<QuantityValidateConstraint, Integer> {

    @Override
    public void initialize(QuantityValidateConstraint quantityValidateConstraint) {
    }

    @Override
    public boolean isValid(Integer quantity, ConstraintValidatorContext context) {
        return quantity >= 0;
    }
}
