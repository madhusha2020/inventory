package com.watersolution.inventory.component.management.grn.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.entity.supplier.model.db.Supplier;
import com.watersolution.inventory.component.management.payment.supplier.model.db.SupplierPayment;
import com.watersolution.inventory.component.management.purchase.model.db.PurchaseOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "purchase")
@EqualsAndHashCode(callSuper = false)
public class Purchase extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "purchase_seq", name = "purchase_seq")
    private long id;

    @Column(name = "code")
    private String code;

    @Lob
    private String description;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "total")
    private BigDecimal total;

    @JsonBackReference(value = "supplierpurchase")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @JsonManagedReference(value = "purchases")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "purchaseorder_id")
    private PurchaseOrder purchaseOrder;

    @ToString.Exclude
    @JsonManagedReference(value = "purchase")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchase", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<PurchaseItems> purchaseItems;

    @JsonBackReference(value = "supplierpayment")
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "purchase", fetch = FetchType.LAZY, orphanRemoval = true)
    private SupplierPayment supplierPayment;

    public Purchase() {

    }
}
