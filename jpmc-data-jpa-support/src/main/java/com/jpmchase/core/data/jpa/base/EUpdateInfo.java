//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.data.jpa.base;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EntityListeners;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners({AuditingEntityListener.class})
@Embeddable
public class EUpdateInfo {
    @LastModifiedDate
    @Column(
        name = "UPDATED_ON",
        nullable = true
    )
    private ZonedDateTime lastUpdatedOn;
    @LastModifiedBy
    @Column(
        name = "UPDATED_BY",
        nullable = true
    )
    private String lastUpdatedBy;

    public EUpdateInfo() {
    }

    public EUpdateInfo(ZonedDateTime lastUpdatedOn, String lastUpdatedBy) {
        this.lastUpdatedOn = lastUpdatedOn;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public ZonedDateTime getLastUpdatedOn() {
        return this.lastUpdatedOn;
    }

    public EUpdateInfo setLastUpdatedOn(ZonedDateTime lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
        return this;
    }

    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    public EUpdateInfo setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public String toString() {
        return "Updated by - " + this.lastUpdatedBy + " on " + this.lastUpdatedOn;
    }
}
