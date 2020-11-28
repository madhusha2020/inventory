package com.watersolution.inventory.component.management.order.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customcompound")
@EqualsAndHashCode(callSuper = false)
public class CustomerCompound extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_compound_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "customer_compound_seq", name = "customer_compound_seq")
    private long id;

    @Column(name = "name")
    private String name;

    @Lob
    private String description;

    @JsonBackReference(value = "customcompound")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private Order order;

    public CustomerCompound() {
    }
}
