package com.watersolution.inventory.component.management.supplier.refund.model.db;

import com.watersolution.inventory.component.common.model.db.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "supplier")
@EqualsAndHashCode(callSuper = false)
public class SupplierRefund extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplier_refund_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "supplier_refund_seq", name = "supplier_refund_seq")
    private long id;

    @NotBlank(message = "Refund code must not be blank")
    @Column(name = "code")
    private String code;

    @NotBlank(message = "Reason must not be blank")
    @Column(name = "reason")
    private String reason;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "amount")
    private BigDecimal amount;

    @Lob
    private String description;

    @Column(name = "chequeno")
    private String chequeno;

    @Column(name = "chequebank")
    private String chequebank;

    @Column(name = "chequebranch")
    private String chequebranch;

    @Column(name = "chequedate")
    private LocalDate chequedate;

    public SupplierRefund() {

    }
}
