package com.watersolution.inventory.component.management.test.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.entity.customer.model.db.Customer;
import com.watersolution.inventory.component.management.payment.customer.model.db.CustomerPayment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "chemicaltest")
@EqualsAndHashCode(callSuper = false)
public class ChemicalTest extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chemical_test_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "chemical_test_seq", name = "chemical_test_seq")
    private long id;

    @Column(name = "code")
    private String code;

    @Lob
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "dorequested")
    private LocalDate dorequested;

    @Column(name = "dotested")
    private LocalDate dotested;

    @Column(name = "dodone")
    private LocalDate dodone;

    @Lob
    private String result;

    @Lob
    private String address;

    @JsonBackReference(value = "customerchemicaltest")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonBackReference(value = "customerpaymentchemicaltest")
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "chemicalTest", fetch = FetchType.LAZY, orphanRemoval = true)
    private CustomerPayment customerPayment;

    public ChemicalTest() {
    }
}
