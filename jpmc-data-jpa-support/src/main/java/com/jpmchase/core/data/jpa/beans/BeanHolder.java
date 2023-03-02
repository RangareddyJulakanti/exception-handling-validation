//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.data.jpa.beans;

import javax.inject.Inject;

import com.jpmchase.core.common.validation.Assert;
import com.jpmchase.core.common.validation.ExceptionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component("condorDataJpaSupportBeanHolder")
public class BeanHolder implements InitializingBean {
    @Inject
    Assert asserts;
    @Inject
    ExceptionFactory exFactory;
    private static BeanHolder instance;

    public BeanHolder() {
    }

    public static Assert asserts() {
        return instance.asserts;
    }

    public static ExceptionFactory exFactory() {
        return instance.exFactory;
    }

    public void afterPropertiesSet() throws Exception {
        instance = this;
    }
}
