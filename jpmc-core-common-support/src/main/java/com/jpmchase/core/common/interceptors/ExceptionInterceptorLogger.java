//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.common.interceptors;


import com.jpmchase.core.common.utils.Stringifier;
import com.jpmchase.core.common.validation.Assert;
import com.jpmchase.core.common.validation.BaseException;
import com.jpmchase.core.common.validation.ExceptionFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionInterceptorLogger {
    private static final Logger log = LoggerFactory.getLogger(ExceptionInterceptorLogger.class);
    private Assert asser;
    private ExceptionFactory exceptionFactory;
    private Stringifier stringifier;

    public ExceptionInterceptorLogger(Assert asser, Stringifier stringifier, ExceptionFactory exceptionFactory) {
        this.asser = asser;
        this.stringifier = stringifier;
        this.exceptionFactory = exceptionFactory;
    }

    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        this.afterThrowingEx(joinPoint, e);
    }

    public void afterThrowingEx(JoinPoint joinPoint, BaseException be) {
        be.setMethodInfo(this.toString(joinPoint));
        log.error(String.format("Call to API [%s] failed due to [%s]", be.getMethodInfo(), be.getLocalizedMessage()));
    }

    public void afterThrowingEx(JoinPoint joinPoint, Throwable subclass) {
        BaseException be = this.exceptionFactory.wrap(subclass, BaseException.class, "INTERNAL_ERROR", new Object[]{"cause", subclass.getMessage()});
        this.afterThrowingEx(joinPoint, be);
    }

    public String toString(JoinPoint joinPoint) {
        try {
            MethodSignature signature = (MethodSignature)joinPoint.getSignature();
            String format = "%s with args [%s]";
            StringBuilder builder = new StringBuilder();
            Class<?>[] parameterTypes = signature.getParameterTypes();

            for(int i = 0; i < parameterTypes.length; ++i) {
                if (i > 0) {
                    builder.append(", ");
                }

                builder.append("args").append(i).append("::");
                builder.append(this.stringifier.stringify(joinPoint.getArgs()[i], parameterTypes[i]));
            }

            return String.format(format, signature.toString(), builder.toString());
        } catch (Throwable var7) {
            return "METHOD_INFO_CANNOT_BE_RETRIEVED";
        }
    }
}
