package com.watersolution.inventory.component.common.validator.annotations;

import com.watersolution.inventory.component.common.validator.QuantityValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = QuantityValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface QuantityValidateConstraint {
    String message() default "Invalid Quantity";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
