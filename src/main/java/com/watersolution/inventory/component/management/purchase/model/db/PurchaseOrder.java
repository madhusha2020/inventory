package com.watersolution.inventory.component.management.purchase.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.entity.supplier.model.db.Supplier;
import com.watersolution.inventory.component.management.grn.model.db.Purchase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "purchaseorder")
@EqualsAndHashCode(callSuper = false)
public class PurchaseOrder extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_order_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "purchase_order_seq", name = "purchase_order_seq")
    private long id;

    @Column(name = "code")
    private String code;

    @Lob
    private String description;

    @Column(name = "doordered")
    private LocalDate doordered;

    @Column(name = "dorequired")
    private LocalDate dorequired;

    @Column(name = "dorecived")
    private LocalDate dorecived;

    @JsonBackReference(value = "supplierpurchaseorder")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ToString.Exclude
    @JsonManagedReference(value = "purchaseorder")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchaseOrder", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<PurchaseOrderItems> purchaseOrderItems;

    @JsonBackReference(value = "purchases")
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "purchaseOrder", fetch = FetchType.LAZY, orphanRemoval = true)
    private Purchase purchase;

    @Transient
    private long supplierId;

    @Transient
    private String supplierName;

    @Transient
    private String supplierType;

    public PurchaseOrder() {

    }
}
