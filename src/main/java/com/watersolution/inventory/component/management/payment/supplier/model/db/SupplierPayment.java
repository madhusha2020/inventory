package com.watersolution.inventory.component.management.payment.supplier.model.db;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watersolution.inventory.component.management.grn.model.db.Purchase;
import com.watersolution.inventory.component.management.payment.common.Payment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "supplierpayment")
@EqualsAndHashCode(callSuper = false)
public class SupplierPayment extends Payment {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplier_payment_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "supplier_payment_seq", name = "supplier_payment_seq")
    private long id;

    @Column(name = "chequeno")
    private String chequeno;

    @Column(name = "chequebank")
    private String chequebank;

    @Column(name = "chequebranch")
    private String chequebranch;

    @Column(name = "chequedate")
    private LocalDate chequedate;

    @JsonManagedReference(value = "supplierpayment")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @Transient
    private long purchaseOrderId;

    public SupplierPayment() {

    }
}
