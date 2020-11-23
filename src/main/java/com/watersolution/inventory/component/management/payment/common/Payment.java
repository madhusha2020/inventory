package com.watersolution.inventory.component.management.payment.common;

import com.watersolution.inventory.component.common.model.db.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class Payment extends Auditable {

    @Column(name = "code")
    private String code;

    @Lob
    private String description;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "chequeno")
    private String chequeno;

    @Column(name = "chequebank")
    private String chequebank;

    @Column(name = "chequebanch")
    private String chequebanch;

    @Column(name = "chequedate")
    private String chequedate;
}
