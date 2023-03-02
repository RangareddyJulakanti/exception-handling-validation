//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.common.validation.spring;

import java.lang.annotation.Annotation;
import javax.validation.Validator;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.AbstractAdvisingBeanPostProcessor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.annotation.Validated;

public class CommonMethodValidationPostProcessor extends AbstractAdvisingBeanPostProcessor implements InitializingBean {
    private Class<? extends Annotation> validatedAnnotationType = Validated.class;
    private Validator validator;

    public CommonMethodValidationPostProcessor(Validator validator) {
        this.validator = validator;
    }

    public void afterPropertiesSet() {
        Pointcut pointcut = new AnnotationMatchingPointcut(this.validatedAnnotationType, true);
        Advice advice = new CommonMethodValidationInterceptor(this.validator);
        this.advisor = new DefaultPointcutAdvisor(pointcut, advice);
    }
}
