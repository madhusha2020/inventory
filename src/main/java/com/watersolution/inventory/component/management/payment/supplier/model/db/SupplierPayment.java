package com.watersolution.inventory.component.management.payment.supplier.model.db;

import com.watersolution.inventory.component.management.payment.common.Payment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

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

    public SupplierPayment() {
    }
}
