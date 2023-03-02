//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public final class CondorValidatingProcessor implements ConstraintValidator<ValidCondor, Object> {
    public CondorValidatingProcessor() {
    }

    public void initialize(ValidCondor constraintAnnotation) {
    }

    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        return BeanHolder.getInstance().getValidationFactory().isValid(value, constraintValidatorContext);
    }
}
