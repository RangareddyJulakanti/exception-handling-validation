//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.common.validation;

import java.util.Locale;
import javax.validation.MessageInterpolator;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.springframework.context.i18n.LocaleContextHolder;

public class LocaleAwareMessageInterpolator extends ResourceBundleMessageInterpolator {
    public LocaleAwareMessageInterpolator() {
    }

    public String interpolate(String messageTemplate, MessageInterpolator.Context context) {
        Locale locale = LocaleContextHolder.getLocale();
        return this.interpolate(messageTemplate, context, locale);
    }

    public String interpolate(String messageTemplate, MessageInterpolator.Context context, Locale locale) {
        return super.interpolate(messageTemplate, context, locale);
    }
}
