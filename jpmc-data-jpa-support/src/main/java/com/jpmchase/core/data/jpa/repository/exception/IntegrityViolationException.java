//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.data.jpa.repository.exception;

public class IntegrityViolationException extends RepositoryException {
    public static final String CODE = "REPOSITORY.INTEGRITY_VIOLATION_EXCEPTION";
    public static final String FORMAT = "Data integrity violation due to constraint [$constraint$].";

    public IntegrityViolationException() {
    }

    public IntegrityViolationException(String code, Object[] args, String message) {
        super(code, args, message);
    }

    public IntegrityViolationException(String code, Object[] args, Throwable cause) {
        super(code, args, cause);
    }

    public IntegrityViolationException(String code, Object[] args, String message, Throwable cause) {
        super(code, args, message, cause);
    }
}
