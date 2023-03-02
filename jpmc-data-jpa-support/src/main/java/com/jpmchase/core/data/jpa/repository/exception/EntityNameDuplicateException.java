//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.data.jpa.repository.exception;

public class EntityNameDuplicateException extends EntityDuplicateException {
    public static final String KEY_TYPE = "type";
    public static final String KEY_NAME = "name";
    public static final String CODE = "REPOSITORY.ENTITY_NAME_DUPLICATE";
    public static final String FORMAT = "Element for type [$type$] with name [$name$] already exisits.";

    public EntityNameDuplicateException() {
    }

    public EntityNameDuplicateException(String code, Object[] args, String message) {
        super(code, args, message);
    }

    public EntityNameDuplicateException(String code, Object[] args, Throwable cause) {
        super(code, args, cause);
    }

    public EntityNameDuplicateException(String code, Object[] args, String message, Throwable cause) {
        super(code, args, message, cause);
    }
}
