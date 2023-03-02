//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.data.jpa.base;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import com.jpmchase.core.data.jpa.configurations.PersistableEntityListener;
import org.hibernate.annotations.TypeDef;

@MappedSuperclass
@EntityListeners({PersistableEntityListener.class})
public abstract class AbstractPersistable {
    @Column(
            name = "NAME",
            nullable = true,
            length = 100
    )
    protected String name;
    public AbstractPersistable() {
    }
    protected AbstractPersistable( String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ConstraintType getConstraintType(String name) {
        return AbstractPersistable.ConstraintType.Others;
    }

    public static enum ConstraintType {
        UniqueName,
        Unique,
        Others;

        private ConstraintType() {
        }
    }
}
