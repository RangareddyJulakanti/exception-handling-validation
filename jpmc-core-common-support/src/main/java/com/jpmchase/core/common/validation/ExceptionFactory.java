//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.common.validation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

import com.jpmchase.core.common.validation.api.CodeToMessageFormatter;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;

public class ExceptionFactory {
    private Logger logger = LoggerFactory.getLogger(ExceptionFactory.class);
    private static final String ASSERT_ERROR = "Assert Error.";
    private CodeToMessageFormatter codeToMessageFormatter;

    public ExceptionFactory(CodeToMessageFormatter codeToMessageFormatter) {
        this.codeToMessageFormatter = codeToMessageFormatter;
    }

    public void throwEx(Class<? extends BaseException> clazz, String code) {
        this.throwEx(clazz, code, (Object[])null);
    }

    public void throwEx(Class<? extends BaseException> clazz, String code, Object[] args) {
        this.throwEx(clazz, code, args, (Supplier)null);
    }

    public void throwEx(Class<? extends BaseException> clazz, String code, Object[] args, Supplier<Object[]> addlInfoSupplier) {
        String message = this.format(code, args);
        this.logAdditionInfo(addlInfoSupplier);
        throw this.newInstance(clazz, code, args, message, (Throwable)null);
    }

    public BaseException wrap(Throwable ex, Class<? extends BaseException> clazz, String code) {
        return this.wrap(ex, clazz, code, (Object[])null);
    }

    public BaseException wrap(Throwable ex, Class<? extends BaseException> clazz, String code, Object[] args) {
        return this.wrap(ex, clazz, code, args, (Supplier)null);
    }

    public BaseException wrap(Throwable ex, Class<? extends BaseException> clazz, String code, Object[] args, Supplier<Object[]> addlInfoSupplier) {
        String message = this.format(code, args);
        this.logAdditionInfo(addlInfoSupplier);
        return this.newInstance(clazz, code, args, message, ex);
    }

    public BaseException createEx(Class<? extends BaseException> clazz, String code) {
        return this.createEx(clazz, code, (Object[])null);
    }

    public BaseException createEx(Class<? extends BaseException> clazz, String code, Object[] args) {
        return this.createEx(clazz, code, args, (Supplier)null);
    }

    public BaseException createEx(Class<? extends BaseException> clazz, String code, Object[] args, Supplier<Object[]> addlInfoSupplier) {
        String message = this.format(code, args);
        this.logAdditionInfo(addlInfoSupplier);
        return this.newInstance(clazz, code, args, message, (Throwable)null);
    }

    void logAdditionInfo(Supplier<Object[]> addlInfoSupplier) {
        if (addlInfoSupplier != null) {
            StringBuilder sb = new StringBuilder();
            Object[] infoItems = (Object[])addlInfoSupplier.get();

            for(int i = 0; i < infoItems.length; i += 2) {
                sb.append(infoItems[i]);
                if (i + 1 < infoItems.length) {
                    sb.append(":").append(infoItems[i + 1]);
                }

                sb.append("\n");
            }

            this.logger.error(sb.toString());
        }
    }

    private String format(String code, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        String pattern = this.codeToMessageFormatter.message(code, "");
        this.logger.trace("Creating localized message using locale {}, code {},args {}, pattern {}", new Object[]{locale, code, args, pattern});
        Map<String, Object> argsMap = toMap(Object.class, args);
        if (argsMap != null && !argsMap.isEmpty()) {
            if (pattern.length() == 0) {
                return argsMap.toString();
            } else {
                String message = StrSubstitutor.replace(pattern, argsMap, "$", "$");
                return message;
            }
        } else {
            return pattern;
        }
    }

    private BaseException newInstance(Class<? extends BaseException> clazz, String code, Object[] args, String message, Throwable ex) {
        try {
            BaseException newInstance = null;
            Constructor constructor;
            if (message.length() != 0) {
                if (ex != null) {
                    constructor = clazz.getConstructor(String.class, Object[].class, String.class, Throwable.class);
                    newInstance = (BaseException)constructor.newInstance(code, args, message, ex);
                } else {
                    constructor = clazz.getConstructor(String.class, Object[].class, String.class);
                    newInstance = (BaseException)constructor.newInstance(code, args, message);
                }
            } else if (ex != null) {
                constructor = clazz.getConstructor(String.class, Object[].class, Throwable.class);
                newInstance = (BaseException)constructor.newInstance(code, args, ex);
            } else {
                newInstance = (BaseException)clazz.newInstance();
            }

            Map<String, Object> argsMap = toMap(Object.class, args);
            newInstance.setArgsMap(argsMap);
            if (code != null) {
                String helpText = this.codeToMessageFormatter.message(code + "$HELP", "");
                newInstance.setHelpText(helpText);
            }

            return newInstance;
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException var9) {
            throw new BaseException("Assert Error.", new Object[0], "Assert Error.", var9);
        }
    }

    private static <T> Map<String, T> toMap(Class<? extends Object> T, Object... args) {
        Map<String, T> map = Collections.emptyMap();
        if (args != null) {
            if (args.length % 2 != 0) {
                throw new BaseException("ASSERTION_ERROR", args, "Number of arguments passed needs to be even");
            }

            map = new HashMap();

            for(int i = 0; i < args.length; i += 2) {
                if (!(args[i] instanceof String)) {
                    throw new BaseException("ASSERTION_ERROR", args, "Argument at odd number needs to be of type string");
                }

                ((Map)map).put((String)args[i], args[i + 1]);
            }
        }

        return (Map)map;
    }
}
