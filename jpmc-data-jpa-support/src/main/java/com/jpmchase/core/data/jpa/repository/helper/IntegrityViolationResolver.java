//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.data.jpa.repository.helper;

import com.jpmchase.core.common.validation.BaseException;

public interface IntegrityViolationResolver {
    BaseException create(String var1, Throwable var2);
}
