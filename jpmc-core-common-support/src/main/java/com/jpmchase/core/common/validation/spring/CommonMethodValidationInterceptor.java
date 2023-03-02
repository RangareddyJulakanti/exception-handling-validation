//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.common.validation.spring;


import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import com.jpmchase.core.common.validation.ConstraintValidationException;
import com.jpmchase.core.common.validation.annotation.ValidateException;
import com.jpmchase.core.common.validation.annotation.ValidationGroup;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.validation.beanvalidation.MethodValidationInterceptor;

public class CommonMethodValidationInterceptor extends MethodValidationInterceptor {
    public CommonMethodValidationInterceptor(Validator validator) {
        super(validator);
    }

    public CommonMethodValidationInterceptor() {
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            return super.invoke(invocation);
        } catch (ConstraintViolationException var10) {
            ValidateException validateException = (ValidateException)AnnotationUtils.findAnnotation(invocation.getThis().getClass(), ValidateException.class);
            Class<? extends ConstraintValidationException> validateExceptionClass = validateException != null ? validateException.value() : ConstraintValidationException.class;
            Set<ConstraintViolation<Object>> violations = new HashSet(var10.getConstraintViolations().size());
            Iterator var6 = var10.getConstraintViolations().iterator();

            while(var6.hasNext()) {
                ConstraintViolation<Object> violation = (ConstraintViolation)var6.next();
                violations.add(violation);
            }

            String message = ConstraintValidationException.toMessage(violations);
            ConstraintViolationException cve2 = new ConstraintViolationException(message, violations);
            Constructor<? extends ConstraintValidationException> constructor = validateExceptionClass.getConstructor(ConstraintViolationException.class);
            ConstraintValidationException constraintValidationException = (ConstraintValidationException)constructor.newInstance(cve2);
            throw constraintValidationException;
        }
    }

    protected Class<?>[] determineValidationGroups(MethodInvocation invocation) {
        Class<?>[] classLevelGroups = super.determineValidationGroups(invocation);
        ValidationGroup validatedGroups = (ValidationGroup)AnnotationUtils.findAnnotation(invocation.getMethod(), ValidationGroup.class);
        Class<?>[] methodLevelGroups = validatedGroups != null ? validatedGroups.value() : new Class[0];
        if (methodLevelGroups.length == 0) {
            return classLevelGroups;
        } else {
            int newLength = classLevelGroups.length + methodLevelGroups.length;
            Class<?>[] mergedGroups = (Class[])Arrays.copyOf(classLevelGroups, newLength);
            System.arraycopy(methodLevelGroups, 0, mergedGroups, classLevelGroups.length, methodLevelGroups.length);
            return mergedGroups;
        }
    }
}
