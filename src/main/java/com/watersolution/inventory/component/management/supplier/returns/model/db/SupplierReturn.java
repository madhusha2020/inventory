package com.watersolution.inventory.component.management.supplier.returns.model.db;

import com.watersolution.inventory.component.common.model.db.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "supplier")
@EqualsAndHashCode(callSuper = false)
public class SupplierReturn extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplier_return_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "supplier_return_seq", name = "supplier_return_seq")
    private long id;

    @NotBlank(message = "Refund code must not be blank")
    @Column(name = "code")
    private String code;

    @NotBlank(message = "Reason must not be blank")
    @Column(name = "reason")
    private String reason;

    @Column(name = "doreturned")
    private LocalDate doreturned;

    @Column(name = "dorecived")
    private LocalDate dorecived;

    public SupplierReturn() {

    }
}
