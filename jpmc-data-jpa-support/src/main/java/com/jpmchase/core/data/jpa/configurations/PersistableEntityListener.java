//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.data.jpa.configurations;

import com.jpmchase.core.data.jpa.base.AbstractPersistable;
import com.jpmchase.core.data.jpa.base.EAuditable;
import com.jpmchase.core.data.jpa.base.ECreateInfo;
import com.jpmchase.core.data.jpa.base.EUpdateInfo;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class PersistableEntityListener {
    public PersistableEntityListener() {
    }

    @PrePersist
    public void prePersist(AbstractPersistable e) {
        if (e instanceof EAuditable.Create) {
            EAuditable.Create create = (EAuditable.Create)e;
            create.setCreateInfo(new ECreateInfo(ZonedDateTime.now(ZoneId.of("UTC")), this.getUserName()));
        }

        if (e instanceof EAuditable.CreateDate) {
            EAuditable.CreateDate createDate = (EAuditable.CreateDate)e;
            createDate.setCreatedOn(ZonedDateTime.now(ZoneId.of("UTC")));
        }

        if (e instanceof EAuditable.CreateUser) {
            EAuditable.CreateUser createUser = (EAuditable.CreateUser)e;
            createUser.setCreatedBy(this.getUserName());
        }

        this.preUpdate(e);
    }

    @PreUpdate
    public void preUpdate(AbstractPersistable e) {
        if (e instanceof EAuditable.Update) {
            EAuditable.Update update = (EAuditable.Update)e;
            update.setUpdateInfo(new EUpdateInfo(ZonedDateTime.now(ZoneId.of("UTC")), this.getUserName()));
        }

        if (e instanceof EAuditable.UpdateDate) {
            EAuditable.UpdateDate updateDate = (EAuditable.UpdateDate)e;
            updateDate.setLastUpdatedOn(ZonedDateTime.now(ZoneId.of("UTC")));
        }

        if (e instanceof EAuditable.UpdateUser) {
            EAuditable.UpdateUser updateUser = (EAuditable.UpdateUser)e;
            updateUser.setLastUpdatedBy(this.getUserName());
        }

    }

    private String getUserName() {
        return "-";
    }

    private String getTenantId() {
        return  "-";
    }

}
