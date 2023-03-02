//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.data.jpa.base;

import java.time.ZonedDateTime;

public interface EAuditable {
    public interface CreateUser {
        String getCreatedBy();

        void setCreatedBy(String var1);
    }

    public interface CreateDate {
        ZonedDateTime getCreatedOn();

        void setCreatedOn(ZonedDateTime var1);
    }

    public interface UpdateUser {
        String getLastUpdatedBy();

        void setLastUpdatedBy(String var1);
    }

    public interface UpdateDate {
        ZonedDateTime getLastUpdatedOn();

        void setLastUpdatedOn(ZonedDateTime var1);
    }

    public interface Create {
        ECreateInfo getCreateInfo();

        void setCreateInfo(ECreateInfo var1);
    }

    public interface Update {
        EUpdateInfo getUpdateInfo();

        void setUpdateInfo(EUpdateInfo var1);
    }
}
