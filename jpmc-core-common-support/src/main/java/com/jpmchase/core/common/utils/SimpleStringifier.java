//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.builder.RecursiveToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SimpleStringifier implements Stringifier {
    public static final String PACKAGE_ROOT = "com.essilor";
    private ObjectMapper objectMapper;

    public SimpleStringifier(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    protected final String toString(Object obj, Class<?> clazz) {
        try {
            if (obj.getClass().getName().startsWith("com.essilor")) {
                try {
                    return this.objectMapper.writeValueAsString(obj);
                } catch (Throwable var6) {
                    try {
                        return ToStringBuilder.reflectionToString(obj, new RecursiveToStringStyleEx());
                    } catch (Throwable var5) {
                        return obj.toString();
                    }
                }
            } else {
                return String.valueOf(obj);
            }
        } catch (Throwable var7) {
            return "Unbale to stingify";
        }
    }

    public String stringify(Object object, Class<?> clazz) {
        if (null == object) {
            return "null";
        } else {
            try {
                return this.toString(object, clazz);
            } catch (Throwable var4) {
                return String.valueOf(object);
            }
        }
    }

    private static class RecursiveToStringStyleEx extends RecursiveToStringStyle {
        RecursiveToStringStyleEx() {
            this.setUseShortClassName(true);
            this.setUseIdentityHashCode(false);
        }

        protected boolean accept(Class<?> clazz) {
            return clazz.getName().startsWith("com.essilor");
        }
    }
}
