//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.data.jpa.repository.helper;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.springframework.data.repository.CrudRepository;

public class EntityHelper {
    public EntityHelper() {
    }

    public static String getEntityClassName(CrudRepository repo, String defaultName) {
        Class<?> eClass = getEntity(repo);
        return eClass != null ? eClass.getSimpleName() : defaultName;
    }

    public static Class<?> getEntity(CrudRepository repo) {
        Type clazzes = getGenericType(repo.getClass())[0];
        Type[] jpaClass = getGenericType(getClass(clazzes));
        return getClass(((ParameterizedType)jpaClass[0]).getActualTypeArguments()[0]);
    }

    public static Type[] getGenericType(Class<?> target) {
        if (target == null) {
            return new Type[0];
        } else {
            Type[] types = target.getGenericInterfaces();
            if (types.length > 0) {
                return types;
            } else {
                Type type = target.getGenericSuperclass();
                return type != null && type instanceof ParameterizedType ? new Type[]{type} : new Type[0];
            }
        }
    }

    private static Class<?> getClass(Type type) {
        if (type instanceof Class) {
            return (Class)type;
        } else if (type instanceof ParameterizedType) {
            return getClass(((ParameterizedType)type).getRawType());
        } else if (type instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType)type).getGenericComponentType();
            Class<?> componentClass = getClass(componentType);
            return componentClass != null ? Array.newInstance(componentClass, 0).getClass() : null;
        } else {
            return null;
        }
    }
}
