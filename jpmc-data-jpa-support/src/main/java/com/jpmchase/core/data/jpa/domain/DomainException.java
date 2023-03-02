//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.data.jpa.domain;


import com.jpmchase.core.common.validation.BaseException;

public class DomainException extends BaseException {
    public DomainException() {
    }

    public DomainException(String code, Object[] args, String message) {
        super(code, args, message);
    }

    public DomainException(String code, Object[] args, Throwable cause) {
        super(code, args, cause);
    }

    public DomainException(String code, Object[] args, String message, Throwable cause) {
        super(code, args, message, cause);
    }
}
