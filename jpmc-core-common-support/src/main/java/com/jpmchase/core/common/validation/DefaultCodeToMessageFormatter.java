//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.common.validation;

import java.util.Locale;

import com.jpmchase.core.common.validation.api.CodeToMessageFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class DefaultCodeToMessageFormatter implements CodeToMessageFormatter {
    private Logger logger = LoggerFactory.getLogger(DefaultCodeToMessageFormatter.class);
    private MessageSource messageSource;

    public DefaultCodeToMessageFormatter(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String message(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        return this.messageSource.getMessage(code, (Object[])null, locale);
    }

    public String message(String code, String defaultVal) {
        Locale locale = LocaleContextHolder.getLocale();
        this.logger.trace("Creating localized message using locale {}, code {}", locale, code);
        return this.messageSource.getMessage(code, (Object[])null, defaultVal, locale);
    }
}
