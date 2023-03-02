package com.jpmchase.core.common.interceptors;


import java.lang.reflect.Method;

import com.jpmchase.core.common.utils.Stringifier;
import com.jpmchase.core.common.validation.Assert;
import com.jpmchase.core.common.validation.BaseException;
import com.jpmchase.core.common.validation.ExceptionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;

public class ProfilingThrowsAdvisor implements ThrowsAdvice {
    private static final Logger log = LoggerFactory.getLogger(ProfilingThrowsAdvisor.class);
    private Assert asser;
    private ExceptionFactory exceptionFactory;
    private Stringifier stringifier;

    public ProfilingThrowsAdvisor(Assert asser, Stringifier stringifier, ExceptionFactory exceptionFactory) {
        this.asser = asser;
        this.stringifier = stringifier;
        this.exceptionFactory = exceptionFactory;
    }

    public void afterThrowing(Method method, Object[] args, Object target, BaseException be) {
        be.setMethodInfo(this.toString(method, args, target, be));
        log.error(String.format("Call to %s failed due to %s", be.getMethodInfo(), be.getLocalizedMessage()));
    }

    public void afterThrowing(Method method, Object[] args, Object target, Throwable subclass) {
        BaseException be = this.exceptionFactory.wrap(subclass, BaseException.class, "INTERNAL_ERROR", new Object[]{"cause", subclass.getMessage()});
        be.setMethodInfo(this.toString(method, args, target, subclass));
        log.error(String.format("Call to %s failed due to %s", be.getMethodInfo(), be.getLocalizedMessage()));
    }

    private String toString(Method method, Object[] args, Object target, Throwable subclass) {
        try {
            String className = target.toString();
            className = className.substring(0, className.indexOf("@"));
            String format = "%s.%s(%s)";
            StringBuilder builder = new StringBuilder();
            Class<?>[] parameterTypes = method.getParameterTypes();

            for(int i = 0; i < parameterTypes.length; ++i) {
                if (i > 0) {
                    builder.append(", ");
                }

                builder.append("args").append(i).append("::");
                builder.append(this.stringifier.stringify(args[i], parameterTypes[i]));
            }

            return String.format(format, className, method.getName(), builder.toString());
        } catch (Throwable var10) {
            return "METHOD_INFO_CANNOT_BE_RETRIEVED";
        }
    }
}
