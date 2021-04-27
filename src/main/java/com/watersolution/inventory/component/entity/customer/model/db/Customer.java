package com.watersolution.inventory.component.entity.customer.model.db;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.management.complain.model.db.Complain;
import com.watersolution.inventory.component.management.order.model.db.Order;
import com.watersolution.inventory.component.management.sales.model.db.Sale;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@Table(name = "customer")
@EqualsAndHashCode(callSuper = false)
public class Customer extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(initialValue = 7, sequenceName = "customer_seq", name = "customer_seq")
    private long id;

    @NotBlank(message = "Customer Name must not be blank")
    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Lob
    private String description;

    @NotBlank(message = "Contact must not be blank")
    @Column(name = "contact1")
    private String contact1;

    @Column(name = "contact2")
    private String contact2;

    @Column(name = "type")
    private String type;

    @NotBlank(message = "Email must not be blank")
    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "fax")
    private String fax;

    @ToString.Exclude
    @JsonManagedReference(value = "customer")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Order> orders;

    @ToString.Exclude
    @JsonManagedReference(value = "customersale")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Sale> sales;

    @ToString.Exclude
    @JsonManagedReference(value = "complain")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Complain> complainList;

    @Transient
    private Integer orderCount;

    public Customer() {
    }
}
