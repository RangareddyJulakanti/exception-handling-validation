//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.common.config;


import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.validation.MessageInterpolator;
import javax.validation.Validator;

import com.jpmchase.core.common.interceptors.ExceptionInterceptorLogger;
import com.jpmchase.core.common.utils.SimpleStringifier;
import com.jpmchase.core.common.utils.Stringifier;
import com.jpmchase.core.common.validation.Assert;
import com.jpmchase.core.common.validation.DefaultCodeToMessageFormatter;
import com.jpmchase.core.common.validation.ExceptionFactory;
import com.jpmchase.core.common.validation.LocaleAwareMessageInterpolator;
import com.jpmchase.core.common.validation.api.CodeToMessageFormatter;
import com.jpmchase.core.common.validation.spring.CommonMethodValidationInterceptor;
import com.jpmchase.core.common.validation.spring.CommonMethodValidationPostProcessor;
import com.jpmchase.core.validation.BeanHolder;
import com.jpmchase.core.validation.CondorValidationFactory;
import com.jpmchase.core.validation.CondorValidationFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ComponentScan(
    basePackages = {"com.jpmchase"},
    excludeFilters = {@Filter(
    type = FilterType.REGEX,
    pattern = {".*Test.*"}
)}
)
@EnableAspectJAutoProxy
public class CondorCommonConfig {
    private static final Logger logger = LoggerFactory.getLogger(CondorCommonConfig.class);
    @Resource
    private Environment env;

    public CondorCommonConfig() {
    }

    @Bean
    public CodeToMessageFormatter codeToMessageFormatter() {
        return new DefaultCodeToMessageFormatter(this.messageSource());
    }

    @Bean
    public ExceptionFactory exceptionFactory() {
        return new ExceptionFactory(this.codeToMessageFormatter());
    }

    @Bean
    public Assert asserter(Validator validator, ExceptionFactory exceptionFactory) {
        return new Assert(validator, exceptionFactory);
    }

    @Bean
    public CommonMethodValidationPostProcessor methodValidationPostProcessor() {
        return new CommonMethodValidationPostProcessor(this.validator().getValidator());
    }

    @Bean
    public CommonMethodValidationInterceptor methodValidationInterceptor() {
        return new CommonMethodValidationInterceptor(this.validator().getValidator());
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setValidationMessageSource(this.validationMessageSource());
        validatorFactoryBean.setValidationPropertyMap(this.validatorProperties());
        return validatorFactoryBean;
    }

    @Bean(
        name = {"validationMessageSource"}
    )
    public MessageSource validationMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        String messages = this.env.getProperty("common.jsr303.message.source.modules", "");
        String[] modules = messages.split(",");
        List<String> baseNames = new ArrayList(modules.length + 1);

        for(int i = 0; i < modules.length; ++i) {
            baseNames.add("classpath:i18n/" + modules[i].trim() + "-validation-messages");
        }

        baseNames.add("classpath:ValidationMessages");
        messageSource.setBasenames((String[])baseNames.toArray(new String[0]));
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(-1);
        logger.info("Loaded Validation Message Source {}", messageSource);
        return messageSource;
    }

    private Map<String, String> validatorProperties() {
        Map<String, String> validatorProperties = new HashMap();
        Boolean failFast = (Boolean)this.env.getProperty("hibernate.validator.fail_fast", Boolean.class, Boolean.TRUE);
        validatorProperties.put("hibernate.validator.fail_fast", failFast.toString());
        return validatorProperties;
    }

    private MessageInterpolator localeAwareMessageInterpolator() {
        return new LocaleAwareMessageInterpolator();
    }

    @Bean
    public SimpleStringifier simpleStringifier() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new Jdk8Module());
        DateFormat dateFormat = new ISO8601DateFormat();
        objectMapper.setDateFormat(dateFormat);
        objectMapper.setSerializationInclusion(Include.NON_ABSENT);
        Boolean indent = (Boolean)this.env.getProperty("spring.jackson.serialization.indent_output", Boolean.class, false);
        if (indent) {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        }

        return new SimpleStringifier(objectMapper);
    }

    @Bean(
        name = {"messageSource"}
    )
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        String messages = this.env.getProperty("common.message.source.modules", "");
        String[] modules = messages.split(",");
        String[] baseNames = new String[modules.length];

        for(int i = 0; i < modules.length; ++i) {
            baseNames[i] = "classpath:i18n/" + modules[i].trim() + "-messages";
        }

        messageSource.setBasenames(baseNames);
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(-1);
        logger.info("Loaded Message Source {}", messageSource);
        return messageSource;
    }

    @Bean
    public ExceptionInterceptorLogger exceptionInterceptorLogger(Assert asser, Stringifier stringifier, ExceptionFactory exceptionFactory) {
        return new ExceptionInterceptorLogger(asser, stringifier, exceptionFactory);
    }

    @Bean(
        name = {"condorValidation"}
    )
    public CondorValidationFactory condorValidationFactory() {
        CondorValidationFactory factory = new CondorValidationFactoryImpl();
        BeanHolder.getInstance().setValidationFactory(factory);
        return factory;
    }
}
