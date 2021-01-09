package com.watersolution.inventory.component.management.inventory.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.common.validator.annotations.QuantityValidateConstraint;
import com.watersolution.inventory.component.entity.item.model.db.Item;
import com.watersolution.inventory.component.management.product.disposal.model.db.DisposalInventory;
import com.watersolution.inventory.component.management.sales.model.db.SaleInventory;
import com.watersolution.inventory.component.management.supplier.refund.model.db.SupplierRefundInventory;
import com.watersolution.inventory.component.management.supplier.returns.model.db.SupplierReturnInventory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "inventory")
@EqualsAndHashCode(callSuper = false)
public class Inventory extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "inventory_seq", name = "inventory_seq")
    private long id;

    @NotBlank(message = "Item Code must not be blank")
    @Column(name = "code")
    private String code;

    @Lob
    private String description;

    @QuantityValidateConstraint(message = "Invalid quantity")
    @Column(name = "initqty")
    private Integer initqty;

    @QuantityValidateConstraint(message = "Invalid quantity")
    @Column(name = "qty")
    private Integer qty;

    @Column(name = "doexpire")
    private LocalDate doexpire;

    @JsonBackReference(value = "inventory_item")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "item_id")
    private Item item;

    @ToString.Exclude
    @JsonManagedReference(value = "inventorysale")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventory", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SaleInventory> saleInventoryList;

    @ToString.Exclude
    @JsonManagedReference(value = "disposalInventory")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inventory")
    private List<DisposalInventory> disposalInventoryList;

    @ToString.Exclude
    @JsonManagedReference(value = "supplierreturninventory")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inventory")
    private List<SupplierReturnInventory> supplierReturnInventories;

    @ToString.Exclude
    @JsonManagedReference(value = "supplierreplaceinventory")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inventory")
    private List<SupplierReturnInventory> supplierReplaceInventories;

    @ToString.Exclude
    @JsonManagedReference(value = "supplierrefundinventory")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inventory")
    private List<SupplierRefundInventory> supplierRefundInventories;

    @Transient
    private int disposedQty;

    public Inventory() {
    }
}
