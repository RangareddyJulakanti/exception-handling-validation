//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.data.jpa.repository.helper;


import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Optional;
import javax.inject.Inject;

import com.jpmchase.core.common.validation.Assert;
import com.jpmchase.core.common.validation.BaseException;
import com.jpmchase.core.common.validation.ExceptionFactory;
import com.jpmchase.core.data.jpa.base.AbstractPersistable;
import com.jpmchase.core.data.jpa.base.EAuditable;
import com.jpmchase.core.data.jpa.repository.exception.EntityDuplicateException;
import com.jpmchase.core.data.jpa.repository.exception.EntityNotFoundException;
import com.jpmchase.core.data.jpa.repository.exception.IntegrityViolationException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RepositoryHelperTemplate implements RepositoryHelper {
    @Inject
    Assert assertions;
    @Inject
    ExceptionFactory exceptionFactory;

    public RepositoryHelperTemplate() {
    }

    @Transactional(
        propagation = Propagation.SUPPORTS
    )
    public <T extends AbstractPersistable, ID extends Serializable> T findOneNotNull(JpaRepository<T, ID> rep, ID key, Class<? extends AbstractPersistable> T) {
        return this.findOneNotNull(rep, key, T,   (Class<? extends BaseException> )null, (String)null, (Object[])null);
    }

    @Transactional(
        propagation = Propagation.SUPPORTS
    )
    public <T extends AbstractPersistable, ID extends Serializable> T findOneNotNull(JpaRepository<T, ID> rep, ID key, Class<? extends AbstractPersistable> T, Class<? extends BaseException> exClass) {
        return this.findOneNotNull(rep, key, T, exClass, (String)null, (Object[])null);
    }

    @Transactional(
        propagation = Propagation.SUPPORTS
    )
    public <T extends AbstractPersistable, ID extends Serializable> T findOneNotNull(JpaRepository<T, ID> rep, ID key, Class<? extends AbstractPersistable> T, Class<? extends BaseException> exClass, String code) {
        return this.findOneNotNull(rep, key, T, exClass, code, (Object[])null);
    }



    @Transactional(
        propagation = Propagation.SUPPORTS
    )
    public <T extends AbstractPersistable, ID extends Serializable> T findOneNotNull(
            JpaRepository<T, ID> rep, ID key, Class<? extends AbstractPersistable> T,
            Class<? extends BaseException> exClass,
            String code,
            Object[] args
    ) {
        Optional<T> item = rep.findById(key);
        if (!item.isPresent()) {
            if (exClass == null) {
                exClass = EntityNotFoundException.class;
                if (code == null) {
                    code = "REPOSITORY.ENTITY_NOT_FOUND";
                }
            }

            if (args == null) {
                args = new Object[]{"type", EntityHelper.getEntityClassName(rep, T.getSimpleName()), "id", key};
            }

            throw this.exceptionFactory.createEx(exClass, code, args);
        } else {
            return item.get();
        }
    }


    @Transactional(
        propagation = Propagation.REQUIRED
    )
    public <T extends AbstractPersistable, ID extends Serializable> T saveAndFlush(JpaRepository<T, ID> rep, T entity) {
        return this.saveAndFlush(rep, entity, (IntegrityViolationResolver)null);
    }

    @Transactional(
        propagation = Propagation.REQUIRED
    )
    public <T extends AbstractPersistable, ID extends Serializable> T save(JpaRepository<T, ID> rep, T entity) {
        return this.save(rep, entity, (IntegrityViolationResolver)null);
    }

    @Transactional(
        propagation = Propagation.REQUIRED
    )
    public <T extends AbstractPersistable, ID extends Serializable> T save(JpaRepository<T, ID> rep, T entity, IntegrityViolationResolver resolver) {
            return rep.save(entity);
    }

    @Transactional(
        propagation = Propagation.REQUIRED
    )
    public <T extends AbstractPersistable, ID extends Serializable> T saveAndFlush(JpaRepository<T, ID> rep, T entity, IntegrityViolationResolver resolver) {
        try {
            return rep.saveAndFlush(entity);
        }
        catch (DataIntegrityViolationException var8) {
            if (var8.getCause() instanceof ConstraintViolationException) {
                ConstraintViolationException cve = (ConstraintViolationException)var8.getCause();
                String constraintName = var8.getMessage();
                if (cve.getConstraintName() != null) {
                    constraintName = cve.getConstraintName().substring(cve.getConstraintName().indexOf(".") + 1);
                }

                if (resolver != null) {
                    BaseException exception;
                    if (cve.getConstraintName() != null) {
                        exception = resolver.create(cve.getConstraintName(), cve);
                    } else {
                        exception = resolver.create(constraintName, cve);
                    }

                    if (exception != null) {
                        throw exception;
                    }
                }

                AbstractPersistable.ConstraintType constraintType = entity.getConstraintType(constraintName);
                if (constraintType == AbstractPersistable.ConstraintType.UniqueName && entity instanceof AbstractPersistable) {
                    throw this.exceptionFactory.wrap(var8.getCause(), EntityDuplicateException.class, "REPOSITORY.ENTITY_DUPLICATE." + constraintName, new Object[]{"type", entity.getClass().getSimpleName(), "name", ((AbstractPersistable)entity).getName()});
                } else if (constraintType == AbstractPersistable.ConstraintType.Unique) {
                    throw this.exceptionFactory.wrap(var8.getCause(), EntityDuplicateException.class, "REPOSITORY.ENTITY_DUPLICATE." + constraintName, new Object[]{"constraint", constraintName});
                } else {
                    throw this.exceptionFactory.wrap(var8.getCause(), IntegrityViolationException.class, constraintName, new Object[]{"constraint", constraintName});
                }
            } else {
                throw this.exceptionFactory.wrap(var8, IntegrityViolationException.class, "INTERNAL_ERROR", new Object[]{"cause", var8.getMessage()});
            }
        }
    }
}
