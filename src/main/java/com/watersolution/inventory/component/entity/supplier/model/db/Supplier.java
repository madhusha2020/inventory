package com.watersolution.inventory.component.entity.supplier.model.db;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.management.grn.model.db.Purchase;
import com.watersolution.inventory.component.management.purchase.model.db.PurchaseOrder;
import com.watersolution.inventory.component.management.supplier.returns.model.db.SupplierReturn;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@Table(name = "supplier")
@EqualsAndHashCode(callSuper = false)
public class Supplier extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplier_seq")
    @SequenceGenerator(initialValue = 5, sequenceName = "supplier_seq", name = "supplier_seq")
    private long id;

    @NotBlank(message = "Supplier code must not be blank")
    @Column(name = "code")
    private String code;

    @NotBlank(message = "Supplier Name must not be blank")
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
    @JsonManagedReference(value = "supplierpurchaseorder")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<PurchaseOrder> purchaseOrderList;

    @ToString.Exclude
    @JsonManagedReference(value = "supplierpurchase")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Purchase> purchaseList;

    @ToString.Exclude
    @JsonManagedReference(value = "suppliereturn")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SupplierReturn> supplierReturns;

    @Transient
    private Integer purchaseOrderCount;

    public Supplier() {

    }
}
