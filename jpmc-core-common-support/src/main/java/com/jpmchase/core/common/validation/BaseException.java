//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.common.validation;

import java.util.Map;

public class BaseException extends RuntimeException {
    private String code;
    private Object[] args;
    private Map<String, Object> argsMap;
    private String methodInfo;
    private String helpText;

    public BaseException() {
        this.code = "BASE_EXCEPTION";
    }

    public BaseException(String code, Object[] args, String message) {
        super(message);
        this.code = code;
        this.args = args;
    }

    public BaseException(String code, Object[] args, Throwable cause) {
        super(cause);
        this.code = code;
        this.args = args;
    }

    public BaseException(String code, Object[] args, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.args = args;
    }

    public String getCode() {
        return this.code;
    }

    public Object[] getArgs() {
        return this.args;
    }

    public Map<String, Object> getArgsMap() {
        return this.argsMap;
    }

    public void setArgsMap(Map<String, Object> argsMap) {
        this.argsMap = argsMap;
    }

    public String getMethodInfo() {
        return this.methodInfo;
    }

    public void setMethodInfo(String methodInfo) {
        this.methodInfo = methodInfo;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    public String getHelpText() {
        return this.helpText;
    }
}
