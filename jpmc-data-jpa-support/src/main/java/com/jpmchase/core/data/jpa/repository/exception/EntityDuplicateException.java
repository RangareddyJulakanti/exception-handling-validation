//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.data.jpa.repository.exception;

public class EntityDuplicateException extends IntegrityViolationException {
    public static final String KEY_TYPE = "type";
    public static final String KEY_NAME = "name";
    public static final String CODE = "REPOSITORY.ENTITY_DUPLICATE";
    public static final String FORMAT = "Element for type [$type$] with name [$name$] has data conflict.";

    public EntityDuplicateException() {
    }

    public EntityDuplicateException(String code, Object[] args, String message) {
        super(code, args, message);
    }

    public EntityDuplicateException(String code, Object[] args, Throwable cause) {
        super(code, args, cause);
    }

    public EntityDuplicateException(String code, Object[] args, String message, Throwable cause) {
        super(code, args, message, cause);
    }
}
