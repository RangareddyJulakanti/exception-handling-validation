//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.common.validation;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ElementKind;
import javax.validation.Path;
import org.apache.commons.lang3.text.StrSubstitutor;

public class ConstraintValidationException extends BaseException {
    private ConstraintViolationException cve;

    public ConstraintValidationException(ConstraintViolationException cve) {
        super("CONSTRAINT_VALIDATION_EXCEPTION", new Object[0], cve.getMessage());
        this.cve = cve;
        List<Map<String, Object>> violationInfos = new LinkedList();
        Iterator var3 = cve.getConstraintViolations().iterator();

        while(var3.hasNext()) {
            ConstraintViolation<?> violation = (ConstraintViolation)var3.next();
            Map<String, Object> violationInfo = new HashMap();
            violationInfo.put("messageTemplate", violation.getMessageTemplate());
            if (violation.getLeafBean() != null) {
                violationInfo.put("leaf", violation.getLeafBean().getClass().getSimpleName());
            }

            violationInfo.put("invalidValue", String.valueOf(violation.getInvalidValue()));
            violationInfo.put("message", violation.getMessage());
            violationInfo.put("sPath", String.valueOf(violation.getPropertyPath()));
            violationInfo.put("path", parsePropertyPath(violation));
            if (violation.getRootBeanClass() != null) {
                violationInfo.put("propertyPath", violation.getRootBeanClass().getSimpleName());
            }

            Map<String, String> sAttributes = new HashMap();
            Map<String, Object> attributes = violation.getConstraintDescriptor().getAttributes();
            Iterator var8 = attributes.entrySet().iterator();

            while(var8.hasNext()) {
                Map.Entry<String, Object> attribute = (Map.Entry)var8.next();
                sAttributes.put(attribute.getKey(), String.valueOf(attribute.getValue()));
            }

            violationInfo.put("attributes", sAttributes);
            violationInfos.add(violationInfo);
        }

        Map<String, Object> argsMap = this.getArgsMap();
        if (argsMap == null) {
            argsMap = new HashMap();
        }

        ((Map)argsMap).put("violations", violationInfos);
        this.setArgsMap((Map)argsMap);
    }

    public ConstraintViolationException getCve() {
        return this.cve;
    }

    public static String toMessage(Set<ConstraintViolation<Object>> violations) {
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        Iterator var3 = violations.iterator();

        while(var3.hasNext()) {
            ConstraintViolation<?> violation = (ConstraintViolation)var3.next();
            if (first) {
                first = false;
            } else {
                builder.append("\n");
            }

            List<ParameterInfo> parameterInfos = parsePropertyPath(violation);
            String path = (String)parameterInfos.stream().map((parameterInfo) -> {
                return parameterInfo.info;
            }).collect(Collectors.joining("."));
            builder.append(path);
            builder.append(": ");
            Map<String, Object> attributes = new HashMap();
            attributes.putAll(violation.getConstraintDescriptor().getAttributes());
            Object invalidValue = violation.getInvalidValue();
            attributes.put("validatedValue", invalidValue);
            String message = StrSubstitutor.replace(violation.getMessage(), attributes, "$", "$");
            builder.append(message);
        }

        return builder.toString();
    }

    private static List<ParameterInfo> parsePropertyPath(ConstraintViolation<?> violation) {
        List<ParameterInfo> parameterInfos = new LinkedList();
        parameterInfos.add(new ParameterInfo(ConstraintValidationException.ParameterInfo.Type.Bean, violation.getRootBeanClass().getSimpleName()));
        List<Class<?>> parameterTypes = null;
        Iterator var3 = violation.getPropertyPath().iterator();

        while(var3.hasNext()) {
            Path.Node node = (Path.Node)var3.next();
            String str = node.toString();
            if (node.getKind() == ElementKind.BEAN) {
                parameterInfos.add(new ParameterInfo(ConstraintValidationException.ParameterInfo.Type.Bean, violation.getRootBeanClass().getSimpleName()));
            } else if (node.getKind() == ElementKind.PARAMETER) {
                int index = ((Path.ParameterNode)node).getParameterIndex();
                if (index < violation.getExecutableParameters().length) {
                    Object parameter = violation.getExecutableParameters()[index];
                    if (parameter != null) {
                        str = str + "[" + parameter.getClass().getSimpleName() + "]";
                    } else if (parameterTypes != null && parameterTypes.size() > index) {
                        str = "[" + ((Class)parameterTypes.get(index)).getSimpleName() + "]";
                    }
                } else if (parameterTypes != null && parameterTypes.size() > index) {
                    str = "[" + ((Class)parameterTypes.get(index)).getSimpleName() + "]";
                }

                parameterInfos.add(new ParameterInfo(ConstraintValidationException.ParameterInfo.Type.Parameter, str));
            } else if (node.getKind() == ElementKind.METHOD) {
                parameterTypes = ((Path.MethodNode)node).getParameterTypes();
                parameterInfos.add(new ParameterInfo(ConstraintValidationException.ParameterInfo.Type.Method, str));
            } else {
                parameterInfos.add(new ParameterInfo(ConstraintValidationException.ParameterInfo.Type.Property, str));
            }
        }

        return parameterInfos;
    }

    private static class ParameterInfo {
        @JsonProperty
        private Type type;
        @JsonProperty
        private String info;

        ParameterInfo(Type type, String info) {
            this.type = type;
            this.info = info;
        }

        public String getInfo() {
            return this.info;
        }

        public Type getType() {
            return this.type;
        }

        private static enum Type {
            Bean,
            Method,
            Parameter,
            Property;

            private Type() {
            }
        }
    }
}
