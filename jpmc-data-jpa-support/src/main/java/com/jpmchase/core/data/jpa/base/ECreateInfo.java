//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.data.jpa.base;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

@Embeddable
public class ECreateInfo {
    @CreatedDate
    @Column(
        name = "CREATED_ON",
        nullable = true
    )
    private ZonedDateTime createdOn;
    @CreatedBy
    @Column(
        name = "CREATED_BY",
        nullable = true
    )
    private String createdBy;

    public ECreateInfo() {
    }

    public ECreateInfo(ZonedDateTime createdOn, String createdBy) {
        this.createdOn = createdOn;
        this.createdBy = createdBy;
    }

    public ZonedDateTime getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(ZonedDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String toString() {
        return "Created by - " + this.createdBy + " on " + this.createdOn;
    }
}
