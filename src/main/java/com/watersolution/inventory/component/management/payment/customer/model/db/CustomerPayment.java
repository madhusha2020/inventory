package com.watersolution.inventory.component.management.payment.customer.model.db;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watersolution.inventory.component.management.payment.common.Payment;
import com.watersolution.inventory.component.management.sales.model.db.Sale;
import com.watersolution.inventory.component.management.test.model.db.ChemicalTest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customerpayment")
@EqualsAndHashCode(callSuper = false)
public class CustomerPayment extends Payment {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_payment_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "customer_payment_seq", name = "customer_payment_seq")
    private long id;

    @JsonManagedReference(value = "customerpaymentsale")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @JsonManagedReference(value = "customerpaymentchemicaltest")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "chemicaltest_id")
    private ChemicalTest chemicalTest;

    public CustomerPayment() {
    }
}
