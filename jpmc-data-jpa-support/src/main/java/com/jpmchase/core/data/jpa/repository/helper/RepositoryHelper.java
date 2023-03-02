//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.data.jpa.repository.helper;


import java.io.Serializable;

import com.jpmchase.core.common.validation.BaseException;
import com.jpmchase.core.data.jpa.base.AbstractPersistable;
import com.jpmchase.core.data.jpa.base.EAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryHelper {
    <T extends AbstractPersistable, ID extends Serializable> T findOneNotNull(JpaRepository<T, ID> var1, ID var2, Class<? extends AbstractPersistable> var3);

    <T extends AbstractPersistable, ID extends Serializable> T findOneNotNull(JpaRepository<T, ID> var1, ID var2, Class<? extends AbstractPersistable> var3, Class<? extends BaseException> var4);

    <T extends AbstractPersistable, ID extends Serializable> T findOneNotNull(JpaRepository<T, ID> var1, ID var2, Class<? extends AbstractPersistable> var3, Class<? extends BaseException> var4, String var5);

    <T extends AbstractPersistable, ID extends Serializable> T findOneNotNull(JpaRepository<T, ID> var1, ID var2, Class<? extends AbstractPersistable> var3, Class<? extends BaseException> var4, String var5, Object[] var6);

    <T extends AbstractPersistable, ID extends Serializable> T saveAndFlush(JpaRepository<T, ID> var1, T var2);

    <T extends AbstractPersistable, ID extends Serializable> T save(JpaRepository<T, ID> var1, T var2);

    <T extends AbstractPersistable, ID extends Serializable> T saveAndFlush(JpaRepository<T, ID> var1, T var2, IntegrityViolationResolver var3);

    <T extends AbstractPersistable, ID extends Serializable> T save(JpaRepository<T, ID> var1, T var2, IntegrityViolationResolver var3);
}
