//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.validation;

public class BeanHolder {
    private CondorValidationFactory validationFactory;
    private static BeanHolder instance = new BeanHolder();

    private BeanHolder() {
    }

    public static BeanHolder getInstance() {
        return instance;
    }

    public CondorValidationFactory getValidationFactory() {
        return this.validationFactory;
    }

    public void setValidationFactory(CondorValidationFactory validationFactory) {
        this.validationFactory = validationFactory;
    }
}
