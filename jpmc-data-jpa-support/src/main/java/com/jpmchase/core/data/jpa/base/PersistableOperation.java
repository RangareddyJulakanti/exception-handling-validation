//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.data.jpa.base;

import java.io.Serializable;
import org.springframework.data.domain.Persistable;

public interface PersistableOperation<ID extends Serializable> extends Persistable<ID> {
    PersistableOperation<ID> setId(ID var1);
}
