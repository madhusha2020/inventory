package com.watersolution.inventory.component.management.complain.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.entity.customer.model.db.Customer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "complain")
@EqualsAndHashCode(callSuper = false)
public class Complain extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "complain_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "complain_seq", name = "complain_seq")
    private long id;

    @Column(name = "code")
    private String code;

    @Lob
    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "response")
    private String response;

    @JsonBackReference(value = "complain")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Complain() {

    }
}
