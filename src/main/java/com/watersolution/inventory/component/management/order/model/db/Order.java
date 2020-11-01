package com.watersolution.inventory.component.management.order.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.entity.customer.model.db.Customer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "customerorder")
@EqualsAndHashCode(callSuper = false)
public class Order extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_order_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "customer_order_seq", name = "customer_order_seq")
    private long id;

    @JsonBackReference(value = "customer")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonManagedReference(value = "order")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.LAZY, orphanRemoval = true)
    @ToString.Exclude
    private List<OrderItems> orderItems;

    public Order() {
    }
}
