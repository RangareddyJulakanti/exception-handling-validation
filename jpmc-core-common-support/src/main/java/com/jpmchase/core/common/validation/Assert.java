//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.common.validation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.function.Supplier;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

public class Assert {
    private static final String ASSERT_ERROR = "ASSERT.ERROR";
    private ExceptionFactory exceptionFactory;
    private Validator validator;

    public Assert(Validator validator, ExceptionFactory exceptionFactory) {
        this.validator = validator;
        this.exceptionFactory = exceptionFactory;
    }

    public void notNull(Object value, Class<? extends BaseException> clazz, String code) {
        this.notNull(value, clazz, code, (Supplier)null);
    }

    public void notNull(Object value, Class<? extends BaseException> clazz, String code, Supplier<Object[]> argsSupplier) {
        this.notNull(value, clazz, code, argsSupplier, (Supplier)null);
    }

    public void notNull(Object value, Class<? extends BaseException> clazz, String code, Supplier<Object[]> args, Supplier<Object[]> addlInfo) {
        this.isTrue(value != null, clazz, code, args, addlInfo);
    }

    public void isTrue(boolean expression, Class<? extends BaseException> clazz, String code) {
        this.isTrue(expression, clazz, code, (Supplier)null);
    }

    public void isTrue(boolean expression, Class<? extends BaseException> clazz, String code, Supplier<Object[]> argsSupplier) {
        this.isTrue(expression, clazz, code, argsSupplier, (Supplier)null);
    }

    public void isTrue(boolean expression, Class<? extends BaseException> clazz, String code, Supplier<Object[]> argsSupplier, Supplier<Object[]> addlInfoSupplier) {
        if (!expression) {
            Object[] args = argsSupplier != null ? (Object[])argsSupplier.get() : null;
            this.exceptionFactory.logAdditionInfo(addlInfoSupplier);
            throw this.exceptionFactory.createEx(clazz, code, args);
        }
    }

    /** @deprecated */
    @Deprecated
    public void notNull(Object value, Class<? extends BaseException> clazz, String code, Object[] args) {
        this.isTrue(value != null, clazz, code, () -> {
            return args;
        });
    }

    /** @deprecated */
    @Deprecated
    public void isTrue(boolean expression, Class<? extends BaseException> clazz, String code, Object[] args) {
        this.isTrue(expression, clazz, code, () -> {
            return args;
        });
    }

    public void validate(Object value) {
        this.validate(value, ConstraintValidationException.class);
    }

    public void validate(Object value, Class<? extends ConstraintValidationException> exceptionClazz, Class<?>... groups) {
        if (value == null) {
            throw this.exceptionFactory.createEx(NullValueException.class, "ASSERT.ERROR");
        } else {
            Set<ConstraintViolation<Object>> violations = this.validator.validate(value, groups);
            if (!violations.isEmpty()) {
                try {
                    String message = ConstraintValidationException.toMessage(violations);
                    Constructor<? extends ConstraintValidationException> constructor = exceptionClazz.getConstructor(ConstraintViolationException.class);
                    ConstraintValidationException constraintValidationException = (ConstraintValidationException)constructor.newInstance(new ConstraintViolationException(message, violations));
                    throw constraintValidationException;
                } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException var8) {
                    throw this.exceptionFactory.wrap(var8, BaseException.class, "ASSERT.ERROR");
                }
            }
        }
    }
}
