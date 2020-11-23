package com.watersolution.inventory.component.management.payment.customer.model.db;

import com.watersolution.inventory.component.management.payment.common.Payment;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerpay_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "customerpay_seq", name = "customerpay_seq")
    private long id;

    //sales

    //chemical test
}
