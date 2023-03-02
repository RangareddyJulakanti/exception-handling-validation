//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.common.validation.annotation;

import com.jpmchase.core.common.validation.ConstraintValidationException;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateException {
    Class<? extends ConstraintValidationException> value() default ConstraintValidationException.class;
}
