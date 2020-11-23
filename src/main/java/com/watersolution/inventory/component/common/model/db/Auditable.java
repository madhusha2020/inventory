package com.watersolution.inventory.component.common.model.db;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class Auditable extends ResponseDefault {

    @Column(name = "status")
    private Integer status;

    @Column(name = "createdby")
    private String createdby;

    @Column(name = "createddate")
    private LocalDateTime createddate;

    @Column(name = "modifiedby")
    private String modifiedby;

    @Column(name = "modifieddate")
    private LocalDateTime modifieddate;

    @Transient
    private String userId;

    @Transient
    private String statusDescription;

    public void fillCompulsory(String userName) {
        this.createdby = userName;
        this.modifiedby = userName;
        this.createddate = LocalDateTime.now();
        this.modifieddate = LocalDateTime.now();
    }

    public void fillUpdateCompulsory(String userName) {
        this.modifiedby = userName;
        this.modifieddate = LocalDateTime.now();
    }
}
