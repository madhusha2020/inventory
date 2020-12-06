package com.watersolution.inventory.component.management.sales.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.entity.customer.model.db.Customer;
import com.watersolution.inventory.component.management.delivery.model.db.Delivery;
import com.watersolution.inventory.component.management.order.model.db.Order;
import com.watersolution.inventory.component.management.payment.customer.model.db.CustomerPayment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "sale")
@EqualsAndHashCode(callSuper = false)
public class Sale extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "sale_seq", name = "sale_seq")
    private long id;

    @Column(name = "code")
    private String code;

    @Lob
    private String description;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "total")
    private BigDecimal total;

    @Lob
    private String deliveryaddress;

    @Column(name = "deliverycost")
    private Double deliverycost;

    @JsonBackReference(value = "customersale")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonBackReference(value = "sale")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private Order order;

    @JsonManagedReference(value = "salecustomcompound")
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "sale", fetch = FetchType.LAZY, orphanRemoval = true)
    private SaleCustomCompound saleCustomCompound;

    @ToString.Exclude
    @JsonManagedReference(value = "saleinventory")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sale", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SaleInventory> saleInventoryList;

    @JsonBackReference(value = "customerpaymentsale")
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "sale", fetch = FetchType.LAZY, orphanRemoval = true)
    private CustomerPayment customerPayment;

    @JsonManagedReference(value = "deliverysale")
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "sale", fetch = FetchType.LAZY, orphanRemoval = true)
    private Delivery delivery;

    /**
     * Customer Data
     */
    @Transient
    private String name;

    @Transient
    private String email;

    @Transient
    private String contact1;

    /**
     * Order Data
     */
    @Transient
    private String totalValue;

    @Transient
    private String discountValue;

    public Sale() {
    }
}
