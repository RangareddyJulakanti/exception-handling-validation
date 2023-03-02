//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.validation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CondorValidationFactoryImpl implements CondorValidationFactory {
    private static final Logger log = LoggerFactory.getLogger(CondorValidationFactoryImpl.class);
    Map<Class<?>, CondorValidator> validatorMap = new HashMap();

    public CondorValidationFactoryImpl() {
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        } else {
            Iterator var3 = this.validatorMap.entrySet().iterator();

            Map.Entry validatorEntry;
            do {
                if (!var3.hasNext()) {
                    log.warn("No validator registered for class - " + value.getClass().getName());
                    return true;
                }

                validatorEntry = (Map.Entry)var3.next();
            } while(!((Class)validatorEntry.getKey()).isAssignableFrom(value.getClass()));

            CondorValidator CondorValidator = (CondorValidator)validatorEntry.getValue();
            return CondorValidator.isValid(value, context);
        }
    }

    public void register(Class<?> clazz, CondorValidator validator) {
        this.validatorMap.put(clazz, validator);
    }
}
