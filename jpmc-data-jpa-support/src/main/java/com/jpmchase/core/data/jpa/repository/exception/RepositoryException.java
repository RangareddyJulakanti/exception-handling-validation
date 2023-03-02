//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.data.jpa.repository.exception;


import com.jpmchase.core.common.validation.BaseException;

public class RepositoryException extends BaseException {
    public RepositoryException() {
    }

    public RepositoryException(String code, Object[] args, String message) {
        super(code, args, message);
    }

    public RepositoryException(String code, Object[] args, Throwable cause) {
        super(code, args, cause);
    }

    public RepositoryException(String code, Object[] args, String message, Throwable cause) {
        super(code, args, message, cause);
    }
}
