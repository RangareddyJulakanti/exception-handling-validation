//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.validation;

import javax.validation.ConstraintValidatorContext;

public interface CondorValidationFactory {
    boolean isValid(Object var1, ConstraintValidatorContext var2);

    void register(Class<?> var1, CondorValidator var2);
}
