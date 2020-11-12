package com.watersolution.inventory.component.common.model.db;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.Date;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class Auditable extends ResponseDefault {

    @Column(name = "status")
    private Integer status;

    @Column(name = "createdby")
    private String createdby;

    @Column(name = "createddate")
    private Date createddate;

    @Column(name = "modifiedby")
    private String modifiedby;

    @Column(name = "modifieddate")
    private Date modifieddate;

    @Transient
    private String userId;

    @Transient
    private String statusDescription;

    public void fillCompulsory(String userName) {
        Date now = new Date();
        this.createdby = userName;
        this.modifiedby = userName;
        this.createddate = now;
        this.modifieddate = now;
    }

    public void fillUpdateCompulsory(String userName) {
        Date now = new Date();
        this.modifiedby = userName;
        this.modifieddate = now;
    }
}
