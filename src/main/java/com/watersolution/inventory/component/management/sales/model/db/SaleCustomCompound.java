package com.watersolution.inventory.component.management.sales.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "salecustomcompound")
@EqualsAndHashCode(callSuper = false)
public class SaleCustomCompound extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_customer_compound_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "sale_customer_compound_seq", name = "sale_customer_compound_seq")
    private long id;

    @Column(name = "name")
    private String name;

    @Lob
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "testingperiod")
    private Integer testingperiod;

    @JsonBackReference(value = "salecustomcompound")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    public SaleCustomCompound() {
    }
}
