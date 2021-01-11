package com.watersolution.inventory.component.management.supplier.returns.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.entity.supplier.model.db.Supplier;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "supplierreturn")
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

    @JsonBackReference(value = "suppliereturn")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ToString.Exclude
    @JsonManagedReference(value = "supplierreturn")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplierReturn", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SupplierReturnInventory> supplierReturnInventories;

    public SupplierReturn() {

    }
}
